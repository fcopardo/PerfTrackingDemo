package github.fcopardo.perfdemo.view

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import github.fcopardo.perfdemo.data.FileManager
import github.fcopardo.perfdemo.tracing.EventTracer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import java.io.File

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
            Image(
                bitmap = bitmapState.value!!.bitmap!!,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier.then(Modifier.fillMaxSize())
            )
        }
    }

    @Composable
    actual fun loadNetwork(imageUri: String, modifier : Modifier) {
        val bitmapState: MutableState<LoadedFile?> = remember { mutableStateOf(null) }
        var traceName = ""
        var threadId = 3
        if(bitmapState.value == null || bitmapState.value?.bitmap == null || bitmapState.value?.isAddressEqual(imageUri) == false) {
            traceName = "load_${imageUri}_download"
            EventTracer.instance.trace(traceName, "mainview", System.currentTimeMillis(), 0, threadId)
            FileManager.getInstance().downloadBitmap(imageUri) { decodedBitmap ->
                withContext(Dispatchers.Main) {
                    bitmapState.value = LoadedFile(imageUri).apply {
                        this.bitmap = decodedBitmap
                    }
                }
            }
        } else {
            traceName = "load_${imageUri}_cache"
            threadId = 0
            EventTracer.instance.trace(traceName, "mainview", System.currentTimeMillis(), 0 , threadId)
        }
        if(bitmapState.value?.bitmap!=null){
            Image(
                bitmap = bitmapState.value!!.bitmap!!,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier.then(Modifier.fillMaxSize())
            )
        }
        EventTracer.instance.trace(traceName, "mainview", System.currentTimeMillis(), 0, threadId)
    }
}

class LoadedFile {
    var bitmap : ImageBitmap ?=null
    var address : String = ""
        set(value) {
            if (value != address) bitmap = null
            field = value
        }

    constructor(url : String){
        address = url
    }

    fun isAddressEqual(url : String) : Boolean {
        return address == url
    }
}
