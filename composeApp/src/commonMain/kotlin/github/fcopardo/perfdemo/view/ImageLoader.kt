package github.fcopardo.perfdemo.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class ImageLoader(private var loader : PlatformBoundImageLoader) {

    companion object{
        private var instance : ImageLoader? = null
        private var platformBoundImageLoader : PlatformBoundImageLoader? = null
        fun setPlatformLoader(loader : PlatformBoundImageLoader) : ImageLoader {
            if(instance == null) instance = ImageLoader(loader)
            platformBoundImageLoader = loader
            return instance!!
        }
        fun getInstance(): ImageLoader? {
            return instance
        }
    }

    @Composable
    fun load(image : String?, modifier : Modifier = Modifier){
        if(image.isNullOrEmpty()) return
        if(image.isUrl()){
            println("url is network : $image")
            loadNetwork(image, modifier)
        } else {
            println("url is not network : $image")
            loadLocal(image, modifier)
        }
    }
    @Composable
    private fun loadLocal(imageUri : String, modifier : Modifier = Modifier){
        loader.loadLocal(imageUri, modifier)
    }
    @Composable
    private fun loadNetwork(imageUri : String, modifier : Modifier = Modifier){
        loader.loadNetwork(imageUri, modifier)
    }
}

fun String.isUrl(): Boolean {
    val urlRegex = "^(http://|https://|ftp://)?[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+(/.*)?\$".toRegex()
    return this.matches(urlRegex)
}