package github.fcopardo.perfdemo.models.rest.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SellerAddress (

    @SerialName("comment"      ) var comment     : String?  = null,
    @SerialName("address_line" ) var addressLine : String?  = null,
    @SerialName("id"           ) var id          : String?  = null,
    @SerialName("latitude"     ) var latitude    : String?  = null,
    @SerialName("longitude"    ) var longitude   : String?  = null,
    @SerialName("country"      ) var country     : Country? = Country(),
    @SerialName("state"        ) var state       : State?   = State(),
    @SerialName("city"         ) var city        : City?    = City()

)