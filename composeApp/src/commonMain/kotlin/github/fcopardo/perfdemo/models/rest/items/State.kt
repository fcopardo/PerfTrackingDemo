package github.fcopardo.perfdemo.models.rest.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class State (

  @SerialName("id"   ) var id   : String? = null,
  @SerialName("name" ) var name : String? = null

)