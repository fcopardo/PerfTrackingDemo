package github.fcopardo.perfdemo

import github.fcopardo.perfdemo.data.rest.MlRestApi
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class ApiTest {
    @Test
    fun testSearchCall() {
        val testTerms = mutableListOf<String>("lego", "minifigura", "spider")
        runBlocking {
            var result = MlRestApi.getInstance().searchFor(testTerms)
            println("search result size : ${result.paging?.total}")
            println("search result items total : ${result.results.size}")
        }
    }

    @Test
    fun testItemCall() {
        val testTerms = mutableListOf<String>("lego", "minifigura", "spider")
        runBlocking {
            var result = MlRestApi.getInstance().searchFor(testTerms)
            result.results[0].id?.let {
                println(it)
                var item = MlRestApi.getInstance().getItem(it)
                println("item name: ${item.title}")
                println("item name: ${item.buyingMode}")
                println("item name: ${item.condition}")
                println("item name: ${item.basePrice}")
                println("item name: ${item.subtitle}")
            }?: {
                fail("search was empty")
            }
        }
    }
}