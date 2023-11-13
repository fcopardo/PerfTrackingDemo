package github.fcopardo.perfdemo.models.rest.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Struct (

  @SerialName("number" ) var number : Int?    = null,
  @SerialName("unit"   ) var unit   : String? = null

)