package github.fcopardo.perfdemo

import android.app.Application

class MyApplication : Application() {
    companion object{
        private var instance : MyApplication? = null
        fun getInstance() : MyApplication {
            return instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}