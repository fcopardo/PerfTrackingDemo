package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable

actual class PlatformBoundImageLoader {
    @Composable
    actual fun loadLocal(imageUri: String) {
    }

    @Composable
    actual fun loadNetwork(imageUri: String) {
    }
}