package github.fcopardo.perfdemo.viewmodels

import kotlinx.coroutines.CoroutineScope

interface ScopeProvider {
    fun getScope () : CoroutineScope
}