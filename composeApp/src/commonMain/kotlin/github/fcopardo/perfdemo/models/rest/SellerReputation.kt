package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SellerReputation (

  @SerialName("level_id"            ) var levelId           : String?       = null,
  @SerialName("power_seller_status" ) var powerSellerStatus : String?       = null,
  @SerialName("transactions"        ) var transactions      : Transactions? = Transactions(),
  @SerialName("metrics"             ) var metrics           : Metrics?      = Metrics()

)