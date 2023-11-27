package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

actual class PlatformBoundImageLoader {

    @Composable
    actual fun loadLocal(imageUri: String, modifier: Modifier) {
    }

    @Composable
    actual fun loadNetwork(imageUri: String, modifier: Modifier) {
    }
}