package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PathFromRoot (

  @SerialName("id"   ) var id   : String? = null,
  @SerialName("name" ) var name : String? = null

)