package github.fcopardo.perfdemo.models.rest.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchLocation (

  @SerialName("state" ) var state : State? = State()

)