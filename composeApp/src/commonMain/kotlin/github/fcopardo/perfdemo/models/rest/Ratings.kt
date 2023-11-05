package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ratings (

  @SerialName("negative" ) var negative : Double? = null,
  @SerialName("neutral"  ) var neutral  : Double? = null,
  @SerialName("positive" ) var positive : Double? = null

)