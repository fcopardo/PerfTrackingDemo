package github.fcopardo.perfdemo.models.rest.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableSorts (

  @SerialName("id"   ) var id   : String? = null,
  @SerialName("name" ) var name : String? = null

)