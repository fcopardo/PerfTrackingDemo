package github.fcopardo.commonLib

import github.fcopardo.kimage.Tracer
import kotlinx.cinterop.ExperimentalForeignApi

class IOsTracer {
    companion object{
        @OptIn(ExperimentalForeignApi::class)
        fun startLog(id: String, data: String, description: String, category: String){
            Tracer.logStartWithId(id, data, description, category)
        }

        @OptIn(ExperimentalForeignApi::class)
        fun finishLog(id: String, data: String, description: String, category: String){
            Tracer.logEndWithId(id, data, description, category)
        }
    }
}