package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import github.fcopardo.perfdemo.tracing.EventTracer
import github.fcopardo.perfdemo.viewmodels.MLSearchViewModel
import io.ktor.util.date.getTimeMillis

class MainIosView {
    companion object{
        @Composable
        fun Render(viewModel: MLSearchViewModel = MLSearchViewModel()){
            val time = getTimeMillis()
            //NativeTracer.instance.beginTrace("app_session_$time", false)
            val name = "start_${time}"
            EventTracer.instance.trace(name, "mainview", time)
            val searchTerms = remember { mutableStateOf<String>("") }
            val searchResult = viewModel.searchState

            if(searchResult.success && searchTerms.value.isNotBlank()){
                EventTracer.instance.trace("search_${searchTerms.value}", "mainview", getTimeMillis())
            }

            MainView.MyApp( {
                if(it.isNotBlank()){
                    EventTracer.instance.trace("search_$it", "mainview", getTimeMillis())
                    viewModel.loadSearch(it)
                }
                searchTerms.value = it
            }, {
                MainView.Companion.ItemList(searchResult.data.results)
            })
            EventTracer.instance.trace(name, "mainview", getTimeMillis())
        }
    }
}