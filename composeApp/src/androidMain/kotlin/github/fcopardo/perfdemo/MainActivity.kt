package github.fcopardo.perfdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import github.fcopardo.perfdemo.data.rest.MlRestApi
import github.fcopardo.perfdemo.models.rest.items.MLItem
import github.fcopardo.perfdemo.platform.threading.Scopes
import github.fcopardo.perfdemo.tracing.EventTracer
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
    }

    override fun onDestroy() {
        EventTracer.instance.stop()
        Scopes.end()
        super.onDestroy()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}