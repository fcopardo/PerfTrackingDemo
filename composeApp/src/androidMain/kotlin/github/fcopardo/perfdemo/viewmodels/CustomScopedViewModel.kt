package github.fcopardo.perfdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.fcopardo.perfdemo.concurrency.ScopeProvider
import kotlinx.coroutines.CoroutineScope

abstract class CustomScopedViewModel : ViewModel() {
    var scopeProvider : ScopeProvider = object : ScopeProvider {
        override fun getScope(): CoroutineScope {
            return viewModelScope
        }
    }
}