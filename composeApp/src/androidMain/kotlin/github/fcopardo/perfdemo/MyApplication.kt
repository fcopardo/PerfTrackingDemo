package github.fcopardo.perfdemo

import android.app.Application
import github.fcopardo.perfdemo.cache.AndroidRestCacheProvider
import github.fcopardo.perfdemo.data.rest.MlRestApi
import github.fcopardo.perfdemo.tracing.AndroidJsonWriter
import github.fcopardo.perfdemo.tracing.AndroidTracer
import github.fcopardo.perfdemo.tracing.EventTracer
import github.fcopardo.perfdemo.view.ImageLoader
import github.fcopardo.perfdemo.view.PlatformBoundImageLoader

class MyApplication : Application() {
    companion object{
        private var instance : MyApplication? = null
        fun getInstance() : MyApplication {
            return instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        EventTracer.instance.jsonWriter = AndroidJsonWriter()
        ImageLoader.setPlatformLoader(PlatformBoundImageLoader())
        MlRestApi.getInstance().cacheProvider = AndroidRestCacheProvider()
        AndroidTracer.instance.shouldTrace = false
        instance = this
    }
}