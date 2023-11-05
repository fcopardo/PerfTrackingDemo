package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Seller (

  @SerialName("id"                 ) var id               : Int?              = null,
  @SerialName("nickname"           ) var nickname         : String?           = null,
  @SerialName("car_dealer"         ) var carDealer        : Boolean?          = null,
  @SerialName("real_estate_agency" ) var realEstateAgency : Boolean?          = null,
  @SerialName("_"                  ) var underscore       : Boolean?          = null,
  @SerialName("registration_date"  ) var registrationDate : String?           = null,
  @SerialName("tags"               ) var tags             : ArrayList<String> = arrayListOf(),
  @SerialName("car_dealer_logo"    ) var carDealerLogo    : String?           = null,
  @SerialName("permalink"          ) var permalink        : String?           = null,
  @SerialName("seller_reputation"  ) var sellerReputation : SellerReputation? = SellerReputation()

)