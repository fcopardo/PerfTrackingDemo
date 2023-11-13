package github.fcopardo.perfdemo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import github.fcopardo.perfdemo.models.domain.RepositoryResult
import github.fcopardo.perfdemo.models.rest.search.MLSearch
import github.fcopardo.perfdemo.repositories.MLSearchRepository
import kotlinx.coroutines.launch

class MLSearchViewModel : CustomScopedViewModel() {

    private var loadedPages by mutableStateOf(mutableMapOf<Int, Int>())
    var searchState by mutableStateOf(ViewModelValue<MLSearch>(MLSearch(), true, ""))

    fun loadSearch(terms : String){
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
    }

}