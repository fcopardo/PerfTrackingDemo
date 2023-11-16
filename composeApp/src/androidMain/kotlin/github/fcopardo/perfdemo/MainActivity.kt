package github.fcopardo.perfdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import github.fcopardo.perfdemo.platform.threading.Scopes
import github.fcopardo.perfdemo.tracing.EventTracer
import github.fcopardo.perfdemo.tracing.NativeTracer
import github.fcopardo.perfdemo.view.ImageLoader
import github.fcopardo.perfdemo.view.MainAndroidView
import github.fcopardo.perfdemo.view.PlatformBoundImageLoader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ImageLoader.setPlatformLoader(PlatformBoundImageLoader())
        setContent {
            MainAndroidView.Render()
        }
    }

    override fun onPause(){
        EventTracer.instance.stop()
        NativeTracer.instance.endTrace()
        super.onPause()
    }

    override fun onStop(){
        Scopes.end()
        super.onStop()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}