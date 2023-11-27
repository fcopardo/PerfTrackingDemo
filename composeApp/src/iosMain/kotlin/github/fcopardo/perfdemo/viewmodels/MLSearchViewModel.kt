package github.fcopardo.perfdemo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import github.fcopardo.perfdemo.models.domain.RepositoryResult
import github.fcopardo.perfdemo.models.rest.search.MLSearch
import github.fcopardo.perfdemo.models.view.ViewModelValue
import github.fcopardo.perfdemo.repositories.MLSearchRepository
import github.fcopardo.perfdemo.tracing.EventTracer
import io.ktor.util.date.getTimeMillis
import kotlinx.coroutines.launch

class MLSearchViewModel : CustomScopedViewModel() {

    var searchState by mutableStateOf(ViewModelValue<MLSearch>(MLSearch(), false, ""))
    private var currentTerms = ""

    fun loadSearch(terms : String){
        currentTerms = terms
        EventTracer.instance.trace("search_${terms}_vm", "mainview", getTimeMillis())
        scopeProvider.getScope().launch {
            MLSearchRepository.get().getSearchResults(terms).collect{
                setState(it)
            }
        }
    }

    private fun setState(repoData : RepositoryResult<MLSearch>) {
        if(repoData.isSuccessful()){
            repoData.getValue()?.let { paginatedData->
                searchState = ViewModelValue(paginatedData, true, "")
            }
        } else {
            var newState = ViewModelValue<MLSearch>(MLSearch(), false, repoData.getMessage())
            searchState = newState
        }
        EventTracer.instance.trace("search_${currentTerms}_vm", "mainview", getTimeMillis())
    }
}