package github.fcopardo.perfdemo.models.rest.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableFilters (

  @SerialName("id"     ) var id     : String?           = null,
  @SerialName("name"   ) var name   : String?           = null,
  @SerialName("type"   ) var type   : String?           = null,
  @SerialName("values" ) var values : ArrayList<Values> = arrayListOf()

)