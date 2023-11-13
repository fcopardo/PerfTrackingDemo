package github.fcopardo.perfdemo.models.rest.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SellerAddress (

  @SerialName("city"            ) var city           : City?           = City(),
  @SerialName("state"           ) var state          : State?          = State(),
  @SerialName("country"         ) var country        : Country?        = Country(),
  @SerialName("search_location" ) var searchLocation : SearchLocation? = SearchLocation(),
  @SerialName("id"              ) var id             : Int?            = null

)