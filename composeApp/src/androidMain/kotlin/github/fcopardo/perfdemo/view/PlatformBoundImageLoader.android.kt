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
import coil.compose.AsyncImage
import github.fcopardo.perfdemo.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.BufferedSink
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException
import java.util.UUID

actual class PlatformBoundImageLoader {
    private val client = OkHttpClient()

    @Composable
    actual fun loadLocal(imageUri: String, modifier : Modifier) {

        val bitmapState: MutableState<LoadedFile?> = remember { mutableStateOf(null) }

        var loadNewImage = false
        if(bitmapState.value == null || bitmapState.value!!.isAddressEqual(imageUri)){
            loadNewImage = true
        }
        if(loadNewImage){
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val bitmap = BitmapFactory.decodeStream(File(imageUri).inputStream()).asImageBitmap()
                    withContext(Dispatchers.Main) {
                        bitmapState.value = LoadedFile(imageUri).apply {
                            this.bitmap = bitmap
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
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

        if(bitmapState.value == null || bitmapState.value?.bitmap == null) {

            GlobalScope.launch(Dispatchers.IO) {
                var dir = MyApplication.getInstance().cacheDir
                var targetFilePath = "$dir${File.separator}${UUID.nameUUIDFromBytes(imageUri.toByteArray())}.${imageUri.substringAfterLast(".")}"
                var targetFile = File(targetFilePath)
                if(!targetFile.exists()){
                    val request = Request.Builder()
                        .url(imageUri)
                        .get()
                        .build()

                    client.newCall(request).execute().use { response ->
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")

                        for ((name, value) in response.headers) {
                            println("image request headers $name: $value")
                        }

                        response.body?.let { responseBody ->
                            dir.mkdirs()
                            if(targetFile.createNewFile()){
                                val sink : BufferedSink = targetFile.sink().buffer()
                                sink.writeAll(responseBody.source())
                                sink.close()
                            }
                        }
                    }
                }
                try {
                    val bitmap = BitmapFactory.decodeFile(targetFilePath)
                    val decodedBitmap = bitmap.asImageBitmap()
                    withContext(Dispatchers.Main) {
                        bitmapState.value = LoadedFile(imageUri).apply {
                            this.bitmap = decodedBitmap
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
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
            println("url is loadNetwork, image should be showing up now")
        } else {
            println("url is loadNetwork, loading now")
        }
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
