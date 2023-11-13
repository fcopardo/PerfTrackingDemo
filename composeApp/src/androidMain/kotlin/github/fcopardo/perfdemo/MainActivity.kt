package github.fcopardo.perfdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import github.fcopardo.perfdemo.data.rest.MlRestApi
import github.fcopardo.perfdemo.models.rest.items.MLItem
import github.fcopardo.perfdemo.view.ImageLoader
import github.fcopardo.perfdemo.view.MainAndroidView
import github.fcopardo.perfdemo.view.MainView
import github.fcopardo.perfdemo.view.PlatformBoundImageLoader
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ImageLoader.setPlatformLoader(PlatformBoundImageLoader())
        setContent {
            MainAndroidView.Render()
        }
        runBlocking {
            var result = MlRestApi.getInstance().searchFor(mutableListOf("lego", "gwen"))
            println(result.results.size)
        }

    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}