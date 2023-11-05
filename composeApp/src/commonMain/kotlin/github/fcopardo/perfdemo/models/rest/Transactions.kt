package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Transactions (

  @SerialName("canceled"  ) var canceled  : Int?     = null,
  @SerialName("completed" ) var completed : Int?     = null,
  @SerialName("period"    ) var period    : String?  = null,
  @SerialName("ratings"   ) var ratings   : Ratings? = Ratings(),
  @SerialName("total"     ) var total     : Int?     = null

)