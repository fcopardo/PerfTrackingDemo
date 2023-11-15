package github.fcopardo.perfdemo.tracing.jsonModels

import kotlinx.serialization.Serializable
@Serializable
class SerializedEvent(
    val name: String,
    val cat: String,
    val ph: String,
    val ts: Long,
    val pid: Int,
    val tid: Int,
    val args: Map<String, String>
)