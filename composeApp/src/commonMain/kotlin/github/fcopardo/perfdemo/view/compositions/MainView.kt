package github.fcopardo.perfdemo.view.compositions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import github.fcopardo.perfdemo.models.rest.search.MLSearch
import github.fcopardo.perfdemo.models.view.ViewModelValue
import github.fcopardo.perfdemo.tracing.EventTracer
import github.fcopardo.perfdemo.tracing.NativeTracer
import github.fcopardo.perfdemo.view.composables.MainViewWidgets
import io.ktor.util.date.getTimeMillis


class MainView {
    companion object{
        @Composable
        fun Render(searchResult : ViewModelValue<MLSearch>, onSearch: (String) -> Unit){
            val time = getTimeMillis()
            NativeTracer.instance.beginTrace("app_session_$time", true)
            val name = "start_${time}"
            EventTracer.instance.trace(name, "mainview", time)
            val searchTerms = remember { mutableStateOf<String>("") }

            if(searchResult.success && searchTerms.value.isNotBlank()){
                EventTracer.instance.trace("search_${searchTerms.value}", "mainview", getTimeMillis())
            }

            MainViewWidgets.MyApp( {
                if(it.isNotBlank()){
                    EventTracer.instance.trace("search_$it", "mainview", getTimeMillis())
                    onSearch(it)
                }
                searchTerms.value = it
            }, {
                MainViewWidgets.ItemList(searchResult.data.results)
            })
            EventTracer.instance.trace(name, "mainview", getTimeMillis())
        }
    }
}