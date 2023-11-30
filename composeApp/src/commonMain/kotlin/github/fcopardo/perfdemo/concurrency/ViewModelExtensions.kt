package github.fcopardo.perfdemo.concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob

fun ScopeProvider.Companion.createForUI() : ScopeProvider {
    val scopeProvider : ScopeProvider = object : ScopeProvider {
        private lateinit var scope : CoroutineScope
        override fun getScope(): CoroutineScope {
            if(!::scope.isInitialized) scope = MainScope()
            return scope
        }
    }
    return scopeProvider
}

fun ScopeProvider.Companion.createForJobs() : ScopeProvider {
    val scopeProvider : ScopeProvider = object : ScopeProvider {
        private lateinit var scope : CoroutineScope
        override fun getScope(): CoroutineScope {
            if(!::scope.isInitialized) scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
            return scope
        }
    }
    return scopeProvider
}