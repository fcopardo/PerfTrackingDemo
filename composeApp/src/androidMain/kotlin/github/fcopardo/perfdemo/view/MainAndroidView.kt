package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import github.fcopardo.perfdemo.models.rest.items.MLItem
import github.fcopardo.perfdemo.viewmodels.MLSearchViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import github.fcopardo.perfdemo.models.rest.search.Results
import github.fcopardo.perfdemo.tracing.EventTracer

class MainAndroidView {
    companion object{
        @Composable
        fun Render(viewModel: MLSearchViewModel = viewModel()){
            val name = "start_${System.currentTimeMillis()}"
            EventTracer.instance.trace(name, "mainview", System.currentTimeMillis())
            val searchTerms = remember { mutableStateOf<String>("") }
            val searchResult = viewModel.searchState

            if(searchResult.success && searchTerms.value.isNotBlank()){
                EventTracer.instance.trace("search_${searchTerms.value}", "mainview", System.currentTimeMillis())
            }

            MainView.MyApp( {
                if(it.isNotBlank()){
                    EventTracer.instance.trace("search_$it", "mainview", System.currentTimeMillis())
                    viewModel.loadSearch(it)
                }
                searchTerms.value = it
            }, {
                MainView.Companion.ItemList(searchResult.data.results)
            })
            EventTracer.instance.trace(name, "mainview", System.currentTimeMillis())
        }
    }
}