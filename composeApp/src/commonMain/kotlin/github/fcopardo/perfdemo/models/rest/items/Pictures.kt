package github.fcopardo.perfdemo.models.rest.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Pictures (

  @SerialName("id"         ) var id        : String? = null,
  @SerialName("url"        ) var url       : String? = null,
  @SerialName("secure_url" ) var secureUrl : String? = null,
  @SerialName("size"       ) var size      : String? = null,
  @SerialName("max_size"   ) var maxSize   : String? = null,
  @SerialName("quality"    ) var quality   : String? = null

)