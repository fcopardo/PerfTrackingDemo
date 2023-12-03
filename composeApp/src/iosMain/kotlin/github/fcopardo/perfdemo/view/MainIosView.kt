package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import github.fcopardo.perfdemo.view.compositions.MainView
import github.fcopardo.perfdemo.viewmodels.MLSearchViewModel

class MainIosView {
    companion object{
        @Composable
        fun Render(viewModel: MLSearchViewModel? = null){
            val model = viewModel ?: remember { MLSearchViewModel() }
            val searchResult = model.searchState
            MainView.Render(searchResult) {
                model.loadSearch(it)
            }
        }
    }
}