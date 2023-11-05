package github.fcopardo.perfdemo.data.rest

import github.fcopardo.perfdemo.models.rest.MLSearch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MlRestApi {
    private val api = HttpClient {
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun searchFor() : MLSearch{
        val request = api.get("https://api.mercadolibre.com/sites/MLA/search?category=MLA1055"){
            headers {
                //append(HttpHeaders.Authorization, BuildConfig)
            }
        }.body<MLSearch>()
        return request
    }
}