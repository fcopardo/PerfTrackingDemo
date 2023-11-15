package github.fcopardo.perfdemo.tracing

actual class ClockAccess {
    actual fun getTime(): Long {
        return System.nanoTime()
    }
}