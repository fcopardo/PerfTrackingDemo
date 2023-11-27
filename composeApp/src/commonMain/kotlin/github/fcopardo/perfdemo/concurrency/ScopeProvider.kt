package github.fcopardo.perfdemo.concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

interface ScopeProvider {
    fun getScope () : CoroutineScope

    fun cancel() {
        getScope().cancel()
    }
}