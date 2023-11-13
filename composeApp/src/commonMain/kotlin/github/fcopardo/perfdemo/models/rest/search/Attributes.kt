package github.fcopardo.perfdemo.models.rest.search

import github.fcopardo.perfdemo.models.rest.AnyToStringSerializer
import github.fcopardo.perfdemo.models.rest.ValueStruct
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attributes (

    @SerialName("id"                   ) var id                 : String?           = null,
    @SerialName("name"                 ) var name               : String?           = null,
    @SerialName("value_id"             ) var valueId            : String?           = null,
    @SerialName("value_name"           ) var valueName          : String?           = null,
    @SerialName("attribute_group_id"   ) var attributeGroupId   : String?           = null,
    @SerialName("attribute_group_name" ) var attributeGroupName : String?           = null,
    @SerialName("value_struct"         ) var valueStruct        : ValueStruct?      = ValueStruct(),
    @SerialName("values"               ) var values             : ArrayList<Values> = arrayListOf(),
    @SerialName("source"               ) var source             : Long?              = null,
    @SerialName("value_type"           ) var valueType          : String?           = null

)