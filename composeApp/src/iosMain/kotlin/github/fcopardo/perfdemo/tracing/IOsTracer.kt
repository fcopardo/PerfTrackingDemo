package github.fcopardo.perfdemo.tracing

class IOsTracer private constructor() : PlaformNativeTracer {
    companion object{
        var instance = IOsTracer()
    }
    override fun beginTrace(name: String, systrace: Boolean) {
    }

    override fun endTrace() {

    }
}