package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap

expect class PlatformBoundImageLoader {
    @Composable
    fun loadLocal(imageUri : String, modifier : Modifier = Modifier)
    @Composable
    fun loadNetwork(imageUri : String, modifier : Modifier = Modifier)
}

class LoadedFile {
    var bitmap : ImageBitmap?=null
    var address : String = ""
        set(value) {
            if (value != address) bitmap = null
            field = value
        }

    constructor(url : String){
        address = url
    }

    fun isAddressEqual(url : String) : Boolean {
        return address == url
    }
}