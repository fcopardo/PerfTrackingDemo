package github.fcopardo.perfdemo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import github.fcopardo.perfdemo.concurrency.ScopeProvider
import github.fcopardo.perfdemo.concurrency.createForJobs
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

    var jobScope = ScopeProvider.createForJobs()

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
            Image(
                bitmap = bitmapState.value!!.bitmap!!,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier.then(Modifier.fillMaxSize())
            )
        }
    }
}