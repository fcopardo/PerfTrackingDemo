package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sales (

  @SerialName("period"    ) var period    : String? = null,
  @SerialName("completed" ) var completed : Int?    = null

)