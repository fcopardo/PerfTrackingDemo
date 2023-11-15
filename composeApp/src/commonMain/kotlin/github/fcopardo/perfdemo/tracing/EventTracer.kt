package github.fcopardo.perfdemo.tracing

import github.fcopardo.perfdemo.tracing.jsonModels.SerializedEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EventTracer private constructor() {
    companion object{
        var instance = EventTracer()
    }

    interface FileWriter {
        fun generateJsonFile(data : Map<String, String>)
        fun writeToFile(data : String)
        fun finishFile()
        fun areTracesWritten() : Boolean
    }

    private var scope = CoroutineScope(Dispatchers.Default)
    private var events = mutableMapOf<String, String>()
    private val json = Json { prettyPrint = true }
    var jsonWriter : FileWriter? = null
    fun trace(name : String, categories : List<String>, time : Long, processId : Int = 0, threadId : Int = 0, arguments : Map<String, String>? = mutableMapOf()){
        scope.launch {
            val cat = StringBuilder()
            categories.forEachIndexed { index, s ->
                if(index > 0){
                    cat.append(",")
                }
                cat.append(s)
            }
            val ph = if(events.containsKey("${name}_B")){
                "E"
            } else {
                "B"
            }
            val event = SerializedEvent(name, cat.toString(), ph, time, processId, threadId, arguments!!)
            events["${name}_${ph}"] = json.encodeToString(event)
        }
    }

    fun trace(name : String, category : String, time : Long, processId : Int = 0, threadId : Int = 0, arguments : Map<String, String>? = mutableMapOf()){
        scope.launch {
            val ph = if(events.containsKey("${name}_B")){
                "E"
            } else {
                "B"
            }
            val event = SerializedEvent(name, category, ph, time, processId, threadId, arguments!!)
            events["${name}_${ph}"] = json.encodeToString(event)
        }
    }

    fun trace(name : String, nameAppends : List<String>, category : String, time : Long, processId : Int = 0, threadId : Int = 0, arguments : Map<String, String>? = mutableMapOf()){
        scope.launch {
            val ph = if(events.containsKey("${name}_B")){
                "E"
            } else {
                "B"
            }
            var finalName = StringBuilder()
            nameAppends.forEach {

            }
            val event = SerializedEvent(name, category, ph, time, processId, threadId, arguments!!)
            events["${name}_${ph}"] = json.encodeToString(event)
        }
    }

    fun write(){
        jsonWriter?.let {
            if(it.areTracesWritten()){
                it.finishFile()
            } else {
                it.generateJsonFile(events)
            }
            events.clear()
        }
    }

    fun stop(){
        scope.cancel()
        write()
    }
}