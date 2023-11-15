package github.fcopardo.perfdemo

import android.app.Application
import github.fcopardo.perfdemo.tracing.AndroidJsonWriter
import github.fcopardo.perfdemo.tracing.EventTracer

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
        instance = this
    }
}