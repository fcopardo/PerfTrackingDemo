package github.fcopardo.perfdemo.data

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import github.fcopardo.perfdemo.MyApplication
import github.fcopardo.perfdemo.platform.threading.Scopes
import github.fcopardo.perfdemo.tracing.EventTracer
import io.ktor.util.date.getTimeMillis
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.BufferedSink
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException
import java.util.UUID

class FileManager {

    companion object{
        private var instance = FileManager()
        fun getInstance() : FileManager {
            return instance
        }
    }

    private val client = OkHttpClient()

    fun downloadBitmap(url : String, callback : suspend (bitmap : ImageBitmap)->Unit){
        Scopes.getIOScope().launch {
            var time = getTimeMillis()
            EventTracer.instance.trace("download_${url}_${time}", mutableListOf("mainview", url), getTimeMillis(), 0, 2)
            val dir = MyApplication.getInstance().cacheDir
            val targetFilePath = if(url.lastIndexOf(".")>-1){ 
                "${dir.path}${File.separator}${UUID.nameUUIDFromBytes(url.toByteArray())}.${url.substringAfterLast(".")}"
            } else {
                "${dir.path}${File.separator}${UUID.nameUUIDFromBytes(url.toByteArray())}}"
            }
            
            val targetFile = File(targetFilePath)
            if(!targetFile.exists()){
                val request = Request.Builder()
                    .url(url)
                    .get()
                    .build()
                try{
                    client.newCall(request).execute().use { response ->
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")

                        response.body?.let { responseBody ->
                            dir.mkdirs()
                            if(targetFile.createNewFile()){
                                val sink : BufferedSink = targetFile.sink().buffer()
                                sink.writeAll(responseBody.source())
                                sink.close()
                            }
                        }
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
            try {
                val bitmap = BitmapFactory.decodeFile(targetFilePath)
                val decodedBitmap = bitmap.asImageBitmap()
                callback(decodedBitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            EventTracer.instance.trace("download_${url}_${time}", mutableListOf("mainview", url), getTimeMillis(), 0, 2)
        }
    }
    
    fun getLocalBitmap(uri : String, callback : suspend (bitmap : ImageBitmap) -> Unit){
        Scopes.getIOScope().launch {
            try {
                val bitmap = BitmapFactory.decodeStream(File(uri).inputStream()).asImageBitmap()
                callback(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}