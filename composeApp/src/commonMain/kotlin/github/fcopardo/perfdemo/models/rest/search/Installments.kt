package github.fcopardo.perfdemo.models.rest.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Installments (

  @SerialName("quantity"    ) var quantity   : Int?    = null,
  @SerialName("amount"      ) var amount     : Double? = null,
  @SerialName("rate"        ) var rate       : Double? = null,
  @SerialName("currency_id" ) var currencyId : String? = null

)