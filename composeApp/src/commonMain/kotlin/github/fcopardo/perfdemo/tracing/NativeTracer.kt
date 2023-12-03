package github.fcopardo.perfdemo.tracing

class NativeTracer private constructor() {
    companion object{
        var instance = NativeTracer()
    }

    private var tracer : PlaformNativeTracer = getPlatformNativeTracer()
    fun beginTrace(name : String, systrace : Boolean = true){
        tracer.beginTrace(name, systrace)
    }

    fun endTrace(){
        tracer.endTrace()
    }
}