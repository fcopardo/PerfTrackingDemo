package github.fcopardo.perfdemo.tracing

import github.fcopardo.commonLib.DocumentDirectory
import github.fcopardo.commonLib.File
import github.fcopardo.commonLib.isDirectory
import github.fcopardo.commonLib.mkdirs
import github.fcopardo.commonLib.writeText
import io.ktor.util.date.getTimeMillis
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL

class IosJsonWriter : EventTracer.FileWriter {

    private val traceDir = File(NSFileManager.defaultManager.DocumentDirectory, "kmmTest/perfettoTraces/")
    private var amount = 0
    private var activeFile : NSURL? = null
    private var finished = false

    private fun getFile() : NSURL {
        println("getting file!")
        if(!traceDir.isDirectory) {
            traceDir.mkdirs()
            println("directory created!")
        }
        if(activeFile == null) {
            activeFile = File(traceDir, getTimeMillis().toString()+".json")
            amount = 0
            println("file created")
        }
        return activeFile!!
    }

    override fun generateJsonFile(data: Map<String, String>) {
        val file = getFile()
        val stringData = StringBuilder()
        if(amount == 0) stringData.append("[")
        data.entries.forEach {
            stringData.append(it.value)
        }
        stringData.append("]")
        file.writeText(stringData.toString())
        finished = true
        amount = 0
    }

    override fun writeToFile(data: String) {
        val file = getFile()
        val stringData = StringBuilder()
        if(amount == 0) {
            stringData.append("[")
            amount++
        }
        file.writeText(stringData.toString())
        finished = false
    }

    override fun finishFile() {
        val file = getFile()
        file.writeText("]")
        amount = 0
        finished = true
    }

    override fun areTracesWritten(): Boolean {
        return amount<=0 && finished
    }
}