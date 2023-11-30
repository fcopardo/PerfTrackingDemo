package github.fcopardo.perfdemo.viewmodels

import github.fcopardo.perfdemo.concurrency.ScopeProvider
import github.fcopardo.perfdemo.concurrency.createForUI

abstract class CustomScopedViewModel {
    var scopeProvider : ScopeProvider = ScopeProvider.createForUI()
}