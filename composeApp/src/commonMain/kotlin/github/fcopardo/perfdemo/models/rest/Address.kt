package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address (

  @SerialName("state_id"   ) var stateId   : String? = null,
  @SerialName("state_name" ) var stateName : String? = null,
  @SerialName("city_id"    ) var cityId    : String? = null,
  @SerialName("city_name"  ) var cityName  : String? = null

)