package github.fcopardo.perfdemo.models.rest.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Metrics (

    @SerialName("sales"                 ) var sales               : Sales?               = Sales(),
    @SerialName("claims"                ) var claims              : Claims?              = Claims(),
    @SerialName("delayed_handling_time" ) var delayedHandlingTime : DelayedHandlingTime? = DelayedHandlingTime(),
    @SerialName("cancellations"         ) var cancellations       : Cancellations?       = Cancellations()

)