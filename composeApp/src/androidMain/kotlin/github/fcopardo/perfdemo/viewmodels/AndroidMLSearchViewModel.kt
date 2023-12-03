package github.fcopardo.perfdemo.viewmodels

class AndroidMLSearchViewModel : AndroidCustomScopedViewModel() {
    val viewModel  = MLSearchViewModel()
    fun loadSearch(terms : String) {
        viewModel.loadSearch(terms)
    }
}