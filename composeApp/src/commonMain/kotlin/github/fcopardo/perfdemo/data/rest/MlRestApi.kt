package github.fcopardo.perfdemo.data.rest

import github.fcopardo.perfdemo.Constants
import github.fcopardo.perfdemo.models.rest.items.MLItem
import github.fcopardo.perfdemo.models.rest.search.MLSearch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MlRestApi {

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
    suspend fun searchFor(searchTerms : List<String>) : MLSearch {
        val searchString = StringBuilder()
        searchTerms.forEach {
            searchString.append(it)
            searchString.append("\uFF00%20")
        }
        return search(searchTerms.toString())
    }

    suspend fun searchFor(searchTerms : String) : MLSearch {
        val searchString = StringBuilder()
        searchTerms.replace("  ", "").split(" ").forEach {
            searchString.append(it)
            searchString.append("\uFF00%20")
        }
        return search(searchTerms)
    }

    private suspend fun search(searchTerms : String): MLSearch {
        val request = api.get("https://api.mercadolibre.com/sites/MLC/search?q=$searchTerms"){
            headers {
                append(HttpHeaders.Authorization, Constants.api_key)
            }
        }.body<MLSearch>()
        return request
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