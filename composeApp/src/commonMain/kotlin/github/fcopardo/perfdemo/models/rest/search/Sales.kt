package github.fcopardo.perfdemo.models.rest.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sales (

  @SerialName("period"    ) var period    : String? = null,
  @SerialName("completed" ) var completed : Int?    = null

)