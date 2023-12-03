package github.fcopardo.perfdemo.tracing

interface PlaformNativeTracer {
    fun beginTrace(name : String, systrace : Boolean = true)
    fun endTrace()
}

expect fun getPlatformNativeTracer() : PlaformNativeTracer