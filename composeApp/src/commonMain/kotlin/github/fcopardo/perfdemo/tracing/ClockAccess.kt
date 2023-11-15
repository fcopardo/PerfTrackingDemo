package github.fcopardo.perfdemo.tracing

expect class ClockAccess {
    fun getTime() : Long
}