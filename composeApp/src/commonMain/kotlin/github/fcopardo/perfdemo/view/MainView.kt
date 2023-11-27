package github.fcopardo.perfdemo.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import github.fcopardo.perfdemo.models.rest.search.Results
import github.fcopardo.perfdemo.tracing.EventTracer
import io.ktor.util.date.getTimeMillis
import org.jetbrains.compose.resources.ExperimentalResourceApi

class MainView {

    companion object {

        @Composable
        fun MyApp(onSearch: (String) -> Unit, content: @Composable () -> Unit) {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopBarWithSearch(onSearch)
                    }
                ) { innerPadding ->
                    content()
                }
            }
        }

        @OptIn(ExperimentalComposeUiApi::class)
        @Composable
        fun TopBarWithSearch(onSearch: (String) -> Unit) {
            var text by remember { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current
            TopAppBar(
                title = { Text("My App") },
                navigationIcon = {
                    IconButton(onClick = {
                        EventTracer.instance.write()
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Navigation Drawer Button")
                    }
                },
                actions = {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Search") },
                        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                onSearch(text)
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            }
                        )
                    )
                }
            )
        }

        @OptIn(ExperimentalResourceApi::class)
        @Composable
        fun ItemList(mlItems : List<Results>){
            LazyColumn(modifier = Modifier.padding(4.dp)) {
                items(mlItems, key = {it.id!!}){ item ->
                    val bookmarked = remember { mutableStateOf(false) }
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        val time = getTimeMillis()

                        val args = mutableMapOf<String, String>().apply {
                            item.id?.let {
                                this["itemId"] = it
                            }
                        }

                        val safeId = if(item.id != null){
                            item.id!!
                        } else {
                            ""
                        }

                        val categories = mutableListOf<String>("mainview", safeId)

                        EventTracer.instance.trace("render${item.id}_$time", categories, time, 0, 0, args)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(120.dp)) {

                                val firstImage = if(item.thumbnail != null){
                                    item.thumbnail
                                } else {
                                    ""
                                }
                                ImageLoader.getInstance()!!.load(firstImage)
                                FloatingActionButton(
                                    onClick = {
                                        bookmarked.value = !bookmarked.value
                                    },
                                    modifier = Modifier.align(Alignment.TopEnd).then(Modifier.size(20.dp, 20.dp))
                                ) {
                                    if(bookmarked.value){
                                        Icon(Icons.Filled.Favorite, contentDescription = null)
                                    } else {
                                        Icon(Icons.Outlined.FavoriteBorder, contentDescription = null)
                                    }
                                }
                            }
                            Column(modifier = Modifier.padding(start = 8.dp)) {
                                Text(text = "${item.title}", style = MaterialTheme.typography.body1)
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(text = "${item.price}", style = MaterialTheme.typography.h6)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(text = "${item.address?.cityName}", style = MaterialTheme.typography.body2)
                                Spacer(modifier = Modifier.height(1.dp))
                                Text(text = "${item.condition}", style = MaterialTheme.typography.body2)
                                Spacer(Modifier.weight(1f))
                                Text(text = "${item.installments?.amount} X ${item.installments?.quantity}", style = MaterialTheme.typography.body1)
                            }
                        }
                        EventTracer.instance.trace("render${item.id}_$time", categories, getTimeMillis(), 0 ,0, args)
                    }
                }
            }
        }
    }
}