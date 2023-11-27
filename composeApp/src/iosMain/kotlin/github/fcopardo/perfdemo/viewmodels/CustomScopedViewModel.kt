package github.fcopardo.perfdemo.viewmodels

import github.fcopardo.perfdemo.concurrency.ScopeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class CustomScopedViewModel {
    var scopeProvider : ScopeProvider = object : ScopeProvider {
        private lateinit var scope : CoroutineScope
        override fun getScope(): CoroutineScope {
            if(!::scope.isInitialized) scope = MainScope()
            return scope
        }
    }
}