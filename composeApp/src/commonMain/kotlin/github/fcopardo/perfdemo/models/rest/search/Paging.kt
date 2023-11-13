package github.fcopardo.perfdemo.models.rest.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Paging (

  @SerialName("total"           ) var total          : Int? = null,
  @SerialName("primary_results" ) var primaryResults : Int? = null,
  @SerialName("offset"          ) var offset         : Int? = null,
  @SerialName("limit"           ) var limit          : Int? = null

)