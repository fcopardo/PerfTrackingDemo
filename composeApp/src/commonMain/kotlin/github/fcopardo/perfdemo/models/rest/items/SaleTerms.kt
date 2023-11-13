package github.fcopardo.perfdemo.models.rest.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import github.fcopardo.perfdemo.models.rest.ValueStruct


@Serializable
data class SaleTerms (

    @SerialName("id"           ) var id          : String?           = null,
    @SerialName("name"         ) var name        : String?           = null,
    @SerialName("value_id"     ) var valueId     : String?           = null,
    @SerialName("value_name"   ) var valueName   : String?           = null,
    @SerialName("value_struct" ) var valueStruct : ValueStruct?      = ValueStruct(),
    @SerialName("values"       ) var values      : ArrayList<Values> = arrayListOf(),
    @SerialName("value_type"   ) var valueType   : String?           = null

)