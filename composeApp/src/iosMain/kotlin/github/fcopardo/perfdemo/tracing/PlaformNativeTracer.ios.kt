package github.fcopardo.perfdemo.tracing


actual fun getPlatformNativeTracer(): PlaformNativeTracer {
    return IOsTracer.instance
}