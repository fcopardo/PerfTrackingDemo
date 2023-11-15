package github.fcopardo.perfdemo.repositories

import github.fcopardo.perfdemo.data.rest.MlRestApi
import github.fcopardo.perfdemo.models.domain.RepositoryResult
import github.fcopardo.perfdemo.models.rest.items.MLItem
import github.fcopardo.perfdemo.models.rest.search.MLSearch
import github.fcopardo.perfdemo.tracing.ClockAccess
import github.fcopardo.perfdemo.tracing.EventTracer
import io.ktor.util.date.getTimeMillis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MLSearchRepository private constructor() {
    companion object{
        private var instance = MLSearchRepository()
        fun get() : MLSearchRepository {
            return instance
        }
    }
    suspend fun getSearchResults(searchTerms : List<String>) = flow<RepositoryResult<MLSearch>> {
        EventTracer.instance.trace("search_${searchTerms}_repo", "mainview", getTimeMillis(), 0, 1)
            lateinit var repoResult : RepositoryResult<MLSearch>
            val result = MlRestApi.getInstance().searchFor(searchTerms)
            repoResult = RepositoryResult(true, "", result)
            emit(repoResult)
            EventTracer.instance.trace("search_${searchTerms}_repo", "mainview", getTimeMillis(), 0, 1)
    }.flowOn(Dispatchers.IO)

    suspend fun getSearchResults(searchTerms : String) = flow<RepositoryResult<MLSearch>> {
        EventTracer.instance.trace("search_${searchTerms}_repo", "mainview", getTimeMillis(), 0, 1)
        lateinit var repoResult : RepositoryResult<MLSearch>
        val result = MlRestApi.getInstance().searchFor(searchTerms)
        repoResult = RepositoryResult(true, "", result)
        emit(repoResult)
        EventTracer.instance.trace("search_${searchTerms}_repo", "mainview", getTimeMillis(), 0, 1)
    }.flowOn(Dispatchers.IO)

    suspend fun getItem(id: String) = flow<RepositoryResult<MLItem>> {
        lateinit var repoResult : RepositoryResult<MLItem>
        val result = MlRestApi.getInstance().getItem(id)
        repoResult = RepositoryResult(true, "", result)
        emit(repoResult)
    }.flowOn(Dispatchers.IO)
}