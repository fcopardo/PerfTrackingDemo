package github.fcopardo.perfdemo.tracing

import github.fcopardo.kimage.Tracer
import kotlinx.cinterop.ExperimentalForeignApi

class IOsTracer private constructor() : PlaformNativeTracer {
    companion object{
        var instance = IOsTracer()
    }

    private var activeName = ""
    private var systraceOn = false

    @OptIn(ExperimentalForeignApi::class)
    override fun beginTrace(name: String, systrace: Boolean) {
        if(systrace && !systraceOn){
            activeName = name
            Tracer.logEndWithId(name, "", "", "")
            systraceOn = true
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun endTrace() {
        if (activeName.isNotEmpty() && systraceOn){
            Tracer.logStartWithId(activeName, "", "", "")
            activeName = ""
            systraceOn = false
        }
    }
}