package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValueStruct (

  @Serializable(with=AnyToStringSerializer::class)
  @SerialName("number" ) var number : String?    = null,
  @SerialName("unit"   ) var unit   : String? = null

)