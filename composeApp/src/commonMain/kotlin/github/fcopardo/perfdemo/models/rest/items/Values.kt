package github.fcopardo.perfdemo.models.rest.items

import github.fcopardo.perfdemo.models.rest.ValueStruct
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Values (

  @SerialName("id"     ) var id     : String? = null,
  @SerialName("name"   ) var name   : String? = null,
  @SerialName("struct" ) var struct : ValueStruct? = null

)