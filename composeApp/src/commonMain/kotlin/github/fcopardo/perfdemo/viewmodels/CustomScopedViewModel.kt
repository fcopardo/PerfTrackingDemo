package github.fcopardo.perfdemo.viewmodels

import github.fcopardo.perfdemo.concurrency.ScopeProvider
import github.fcopardo.perfdemo.concurrency.createForUI
import kotlinx.coroutines.cancel

abstract class CustomScopedViewModel : ScopedViewModel {
    private var scopeProvider : ScopeProvider = ScopeProvider.createForUI()
    override fun getScopeProvider(): ScopeProvider {
        return scopeProvider
    }
    override fun setScopeProvider(provider: ScopeProvider) {
        scopeProvider.cancel()
        scopeProvider = provider
    }
}

interface ScopedViewModel {
    fun getScopeProvider() : ScopeProvider
    fun setScopeProvider(provider : ScopeProvider)
}