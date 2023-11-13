package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

expect class PlatformBoundImageLoader {
    @Composable
    fun loadLocal(imageUri : String, modifier : Modifier = Modifier)
    @Composable
    fun loadNetwork(imageUri : String, modifier : Modifier = Modifier)
}