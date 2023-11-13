package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import github.fcopardo.perfdemo.models.rest.items.MLItem
import github.fcopardo.perfdemo.viewmodels.MLSearchViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import github.fcopardo.perfdemo.models.rest.search.Results

class MainAndroidView {
    companion object{
        @Composable
        fun Render(viewModel: MLSearchViewModel = viewModel()){
            val searchTerms = remember { mutableStateListOf<String>() }

            val searchResult = viewModel.searchState

            MainView.MyApp( {
                if(it.isNotBlank()){
                    searchTerms.add(it)
                    println("searchquery adds $it")
                    viewModel.loadSearch(it)
                } else {
                    searchTerms.clear()
                }
            }, {
                MainView.Companion.ItemList(searchResult.data.results)
            })
        }
    }
}