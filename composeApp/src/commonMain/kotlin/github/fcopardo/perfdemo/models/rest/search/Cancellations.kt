package github.fcopardo.perfdemo.models.rest.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cancellations (

  @SerialName("period" ) var period : String? = null,
  @SerialName("rate"   ) var rate   : Double? = null,
  @SerialName("value"  ) var value  : Int?    = null

)