package github.fcopardo.perfdemo.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import github.fcopardo.perfdemo.data.FileManager
import github.fcopardo.perfdemo.tracing.EventTracer
import github.fcopardo.perfdemo.view.composables.MainViewWidgets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual class PlatformBoundImageLoader {

    @Composable
    actual fun loadLocal(imageUri: String, modifier : Modifier) {

        val bitmapState: MutableState<LoadedFile?> = remember { mutableStateOf(null) }

        if(bitmapState.value == null || bitmapState.value?.bitmap == null || bitmapState.value?.isAddressEqual(imageUri) == false){
            FileManager.getInstance().getLocalBitmap(imageUri){ bitmap->
                withContext(Dispatchers.Main) {
                    bitmapState.value = LoadedFile(imageUri).apply {
                        this.bitmap = bitmap
                    }
                }
            }
        }
        if(bitmapState.value?.bitmap!=null){
            MainViewWidgets.ItemImage(bitmapState.value!!.bitmap!!)
        }
    }

    @Composable
    actual fun loadNetwork(imageUri: String, modifier : Modifier) {
        //tryCoil(imageUri, modifier)
        tryCustom(imageUri, modifier)
    }

    @Composable
    private fun tryCustom(imageUri: String, modifier: Modifier){
        val bitmapState: MutableState<LoadedFile?> = remember { mutableStateOf(null) }
        val time = System.currentTimeMillis()
        val traceName = "load_${imageUri}_cache_$time"
        if(bitmapState.value?.isAddressEqual(imageUri)!=true){
            FileManager.getInstance().downloadBitmap(imageUri) { decodedBitmap ->
                withContext(Dispatchers.Main) {
                    bitmapState.value = LoadedFile(imageUri).apply {
                        this.bitmap = decodedBitmap
                    }
                }
            }
        }

        if(bitmapState.value?.bitmap!=null){
            EventTracer.instance.trace(traceName, "mainview", time)
            MainViewWidgets.ItemImage(bitmapState.value!!.bitmap!!)
            EventTracer.instance.trace(traceName, "mainview", System.currentTimeMillis())
        }
    }

    @Composable
    private fun tryCoil(imageUri: String, modifier: Modifier){
        val time = System.currentTimeMillis()
        val traceName = "load_${imageUri}_download_$time"
        EventTracer.instance.trace(traceName, "mainview", time, 0, 0)
        AsyncImage(model = imageUri, contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier.then(Modifier.fillMaxSize()))
        EventTracer.instance.trace(traceName, "mainview", System.currentTimeMillis(), 0, 0)
    }
}
