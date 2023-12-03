package github.fcopardo.perfdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.fcopardo.perfdemo.concurrency.ScopeProvider
import kotlinx.coroutines.CoroutineScope

abstract class AndroidCustomScopedViewModel : ViewModel(), ScopedViewModel {
    private var scopeProvider : ScopeProvider = object : ScopeProvider {
        override fun getScope(): CoroutineScope {
            return viewModelScope
        }
    }
    override fun getScopeProvider(): ScopeProvider {
        return scopeProvider
    }

    override fun setScopeProvider(provider: ScopeProvider) {
        scopeProvider.cancel()
        scopeProvider = provider
    }
}