package github.fcopardo.perfdemo.tracing

import github.fcopardo.perfdemo.MyApplication
import java.io.File

class AndroidJsonWriter : EventTracer.FileWriter {

    private var activeFile : File? = null
    private var lines = 0

    override fun generateJsonFile(data: Map<String, String>) {
        var file = prepareFile()
        if(file.createNewFile()){
            file.appendText("[")
            var amount = 0
            data.forEach { entry ->
                if(amount>0) file.appendText(",")
                file.appendText(entry.value)
                amount++
            }
            file.appendText("]")
        }
    }

    override fun writeToFile(data : String){
        var file = prepareFile()
        if(file.createNewFile()){
            if(lines == 0){
                file.appendText("[")
            } else {
                file.appendText(",")
            }
            file.appendText(data)
            lines++
        }
    }

    override fun finishFile(){
        if(lines > 0){
            activeFile?.appendText("]")
            activeFile = null
            lines = 0
        }
    }

    override fun areTracesWritten() : Boolean {
        return activeFile!=null && lines > 0
    }

    private fun prepareFile() : File {
        if(activeFile == null) {
            val dir = MyApplication.getInstance().cacheDir
            val targetFilePath =
                "${dir.path}${File.separator}trace_${System.currentTimeMillis()}${".json"}"
            activeFile = File(targetFilePath)
        }
        return activeFile!!
    }
}