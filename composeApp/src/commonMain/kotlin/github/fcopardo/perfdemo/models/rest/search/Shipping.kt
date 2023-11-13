package github.fcopardo.perfdemo.models.rest.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Shipping (

  @SerialName("store_pick_up" ) var storePickUp  : Boolean?          = null,
  @SerialName("free_shipping" ) var freeShipping : Boolean?          = null,
  @SerialName("logistic_type" ) var logisticType : String?           = null,
  @SerialName("mode"          ) var mode         : String?           = null,
  @SerialName("tags"          ) var tags         : ArrayList<String> = arrayListOf(),
  @SerialName("benefits"      ) var benefits     : String?           = null,
  @SerialName("promise"       ) var promise      : String?           = null

)