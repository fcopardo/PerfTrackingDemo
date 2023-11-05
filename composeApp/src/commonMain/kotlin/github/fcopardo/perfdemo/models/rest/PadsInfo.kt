package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PadsInfo (

  @SerialName("tracelog" ) var tracelog : ArrayList<String> = arrayListOf()

)