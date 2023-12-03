package github.fcopardo.perfdemo.tracing

import android.os.Debug
import android.os.Trace
import github.fcopardo.perfdemo.MyApplication
import github.fcopardo.perfdemo.platform.threading.Scopes
import kotlinx.coroutines.launch
import java.io.File

class AndroidTracer : PlaformNativeTracer {
    companion object{
        var instance = AndroidTracer()
    }

    private var isTraceActive = false
    private var isMethodSamplingActive = false
    var shouldTrace = false
    override fun beginTrace(name : String, systrace : Boolean){
        if(shouldTrace){
            if(systrace){
                if(!isTraceActive) {
                    Trace.beginSection(name)
                    isTraceActive = true
                }
            }
            if(!isMethodSamplingActive && !systrace){
                Scopes.getIOScope().launch {
                    val dir = MyApplication.getInstance().cacheDir
                    val traceDir = File(dir, "traces")
                    traceDir.mkdirs()
                    val targetFilePath = "${dir.path}${File.separator}traces${File.separator}traceFile_${System.currentTimeMillis()}.trace"
                    val file = File(targetFilePath)
                    if(file.createNewFile()){
                        Debug.startMethodTracing(targetFilePath)
                    }
                }
                isMethodSamplingActive = true
            }
        }
    }

    override fun endTrace(){
        if(isMethodSamplingActive){
            Debug.stopMethodTracing()
            isMethodSamplingActive = false
        }
        if(isTraceActive) {
            Trace.endSection()
            isTraceActive = false
        }
    }
}