package github.fcopardo.perfdemo.tracing

import github.fcopardo.perfdemo.tracing.jsonModels.SerializedEvent

class Event {
    //the name of the event
    var name = ""
    //the category or categories of the event. It is a comma separated list of values.
    var cat = ""
    //the type of event. Possible values are B or E, B for starting an event (Begin) and E for ending it (End).
    var ph = ""
    //timestamp. By default it goes at microsecond level.
    var ts = 0L
    //process id
    var pid = 0
    //thread id
    var tid = 0
    //custom key-value pairs that may be sent alongside the event
    var args = mutableMapOf<String, String>()

    fun toSerialized() : SerializedEvent{
        return SerializedEvent(name, cat, ph, ts, pid, tid, args)
    }
}