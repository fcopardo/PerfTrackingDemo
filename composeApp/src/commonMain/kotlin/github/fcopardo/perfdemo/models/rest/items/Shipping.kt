package github.fcopardo.perfdemo.models.rest.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Shipping (

  @SerialName("mode"          ) var mode         : String?           = null,
  @SerialName("methods"       ) var methods      : ArrayList<String> = arrayListOf(),
  @SerialName("tags"          ) var tags         : ArrayList<String> = arrayListOf(),
  @SerialName("dimensions"    ) var dimensions   : String?           = null,
  @SerialName("local_pick_up" ) var localPickUp  : Boolean?          = null,
  @SerialName("free_shipping" ) var freeShipping : Boolean?          = null,
  @SerialName("logistic_type" ) var logisticType : String?           = null,
  @SerialName("store_pick_up" ) var storePickUp  : Boolean?          = null

)