package github.fcopardo.perfdemo.viewmodels

class AndroidMLSearchViewModel : AndroidCustomScopedViewModel() {
    val viewModel  = MLSearchViewModel()
    init {
        viewModel.setScopeProvider(this.getScopeProvider())
    }
    fun loadSearch(terms : String) {
        viewModel.loadSearch(terms)
    }
}