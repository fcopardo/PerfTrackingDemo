package github.fcopardo.perfdemo.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import github.fcopardo.perfdemo.concurrency.ScopeProvider
import github.fcopardo.perfdemo.concurrency.createForJobs
import github.fcopardo.perfdemo.tracing.EventTracer
import github.fcopardo.perfdemo.view.composables.MainViewWidgets
import io.ktor.util.date.getTimeMillis
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import platform.Foundation.NSFileManager
import platform.Foundation.NSMutableURLRequest
import platform.Foundation.NSURL
import platform.Foundation.NSURLSession
import platform.Foundation.dataTaskWithRequest
import platform.Foundation.setHTTPMethod
import platform.Foundation.writeToFile
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
        val time = getTimeMillis()
        val traceName = "load_${imageUri}_cache_$time"
        if(bitmapState.value?.isAddressEqual(imageUri)!=true){
            jobScope.getScope().launch {
                var time = getTimeMillis()
                EventTracer.instance.trace("download_${imageUri}_${time}", mutableListOf("mainview", imageUri), getTimeMillis(), 0, 2)
                NSURL.URLWithString(imageUri)?.let { url ->
                    val request = NSMutableURLRequest.requestWithURL(url)
                    request.setHTTPMethod("GET")
                    val task = NSURLSession.sharedSession.dataTaskWithRequest(request) { data , res, err ->
                        jobScope.getScope().launch {
                            data?.let {
                                val image = UIImage(it).toImageBitmap()
                                EventTracer.instance.trace("download_${imageUri}_${time}", mutableListOf("mainview", imageUri), getTimeMillis(), 0, 2)
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
            EventTracer.instance.trace(traceName, "mainview", time)
            MainViewWidgets.ItemImage(bitmapState.value!!.bitmap!!)
            EventTracer.instance.trace(traceName, "mainview", getTimeMillis())
        }
    }
}