package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import github.fcopardo.perfdemo.concurrency.ScopeProvider
import github.fcopardo.perfdemo.concurrency.createForJobs
import github.fcopardo.perfdemo.view.composables.MainViewWidgets
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import platform.Foundation.NSMutableURLRequest
import platform.Foundation.NSURL
import platform.Foundation.NSURLSession
import platform.Foundation.dataTaskWithRequest
import platform.Foundation.setHTTPMethod
import platform.UIKit.UIImage

actual class PlatformBoundImageLoader {

    private var jobScope = ScopeProvider.createForJobs()

    @OptIn(ExperimentalForeignApi::class)
    @Composable
    actual fun loadLocal(imageUri: String, modifier: Modifier) {
    }

    @Composable
    actual fun loadNetwork(imageUri: String, modifier: Modifier) {
        val bitmapState: MutableState<LoadedFile?> = remember { mutableStateOf(null) }
        if(bitmapState.value?.isAddressEqual(imageUri)!=true){
            jobScope.getScope().launch {
                NSURL.URLWithString(imageUri)?.let { url ->
                    val request = NSMutableURLRequest.requestWithURL(url)
                    request.setHTTPMethod("GET")
                    val task = NSURLSession.sharedSession.dataTaskWithRequest(request) { data , res, err ->
                        jobScope.getScope().launch {
                            data?.let {
                                val image = UIImage(it).toImageBitmap()
                                withContext(Dispatchers.Main) {
                                    bitmapState.value = LoadedFile(imageUri).apply {
                                        this.bitmap = image
                                    }
                                }
                            }
                        }
                    }
                    task.resume()
                }
            }
        }
        if(bitmapState.value?.bitmap!=null){
            MainViewWidgets.ItemImage(bitmapState.value!!.bitmap!!)
        }
    }
}