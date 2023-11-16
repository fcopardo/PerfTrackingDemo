package github.fcopardo.perfdemo.data.rest

import github.fcopardo.perfdemo.Constants
import github.fcopardo.perfdemo.models.rest.items.MLItem
import github.fcopardo.perfdemo.models.rest.search.MLSearch
import github.fcopardo.perfdemo.tracing.EventTracer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.date.getTimeMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MlRestApi {

    interface CacheProvider {
        fun saveData(key : String, data : String)
        fun getData(key : String) : String
    }

    companion object{
        private var instance = MlRestApi()
        public fun getInstance() : MlRestApi {
            return instance
        }
    }

    private val api = HttpClient {
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    var cacheProvider : CacheProvider? = null
    private val json = Json { prettyPrint = true }
    suspend fun searchFor(searchTerms : List<String>) : MLSearch {
        val searchString = StringBuilder()
        searchTerms.forEach {
            searchString.append(it)
            //searchString.append("\uFF00%20")
            searchString.append("%20")
        }
        return search(searchString.toString())
    }

    suspend fun searchFor(searchTerms : String) : MLSearch {
        val searchString = StringBuilder()
        searchTerms.replace("  ", "").split(" ").forEach {
            searchString.append(it)
            searchString.append("%20")
        }
        return search(searchString.toString())
    }

    private suspend fun search(searchTerms : String): MLSearch {
        try{
            val request = api.get("https://api.mercadolibre.com/sites/MLC/search?q=$searchTerms"){
                headers {
                    append(HttpHeaders.Authorization, Constants.api_key)
                }
            }.body<MLSearch>()
            cacheProvider?.saveData(searchTerms, json.encodeToString(request))
            return request
        }catch(e : Exception){
            e.printStackTrace()
            cacheProvider?.let {
                val data = it.getData(searchTerms)
                if(data.isNotEmpty()) {
                    try {
                        return Json.decodeFromString<MLSearch>(data)
                    }
                    catch(e : Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
        return MLSearch()
    }

    suspend fun getItem(id : String) : MLItem {
        val request = api.get("https://api.mercadolibre.com/items/$id"){
            headers {
                append(HttpHeaders.Authorization, Constants.api_key)
            }
        }.body<MLItem>()
        return request
    }
}