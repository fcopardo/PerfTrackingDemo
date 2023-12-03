package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import github.fcopardo.perfdemo.view.compositions.MainView
import github.fcopardo.perfdemo.viewmodels.AndroidMLSearchViewModel

class MainAndroidView {
    companion object {
        @Composable
        fun Render(viewModel: AndroidMLSearchViewModel? = null){
            val model = viewModel ?: viewModel()
            val searchResult = model.viewModel.searchState
            MainView.Render(searchResult) {
                model.loadSearch(it)
            }
        }
    }
}