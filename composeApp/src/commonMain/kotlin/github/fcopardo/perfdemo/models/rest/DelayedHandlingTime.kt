package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DelayedHandlingTime (

  @SerialName("period" ) var period : String? = null,
  @SerialName("rate"   ) var rate   : Double? = null,
  @SerialName("value"  ) var value  : Int?    = null

)