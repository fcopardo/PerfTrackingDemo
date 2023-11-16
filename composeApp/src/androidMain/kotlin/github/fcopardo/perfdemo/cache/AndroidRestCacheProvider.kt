package github.fcopardo.perfdemo.cache

import github.fcopardo.perfdemo.MyApplication
import github.fcopardo.perfdemo.data.rest.MlRestApi
import github.fcopardo.perfdemo.platform.threading.Scopes
import kotlinx.coroutines.launch
import java.io.File

class AndroidRestCacheProvider : MlRestApi.CacheProvider {
    override fun  saveData(key: String, data: String) {
        Scopes.getIOScope().launch {
            getFile(key).writeText(data)
        }
    }

    override fun getData(key: String): String {
        val file = getFile(key)
        return file.readText()
    }

    private fun getFile(name : String) : File {
        val dir = MyApplication.getInstance().cacheDir
        val restDir = File(dir, "restCache")
        restDir.mkdirs()
        val targetFilePath = "${dir.path}${File.separator}restCache${File.separator}$name.json"
        val file = File(targetFilePath)
        file.createNewFile()
        return file
    }
}