package github.fcopardo.perfdemo.models.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MLSearch (

  @SerialName("site_id"                   ) var siteId                 : String?                     = null,
  @SerialName("country_default_time_zone" ) var countryDefaultTimeZone : String?                     = null,
  @SerialName("paging"                    ) var paging                 : Paging?                     = Paging(),
  @SerialName("results"                   ) var results                : ArrayList<Results>          = arrayListOf(),
  @SerialName("sort"                      ) var sort                   : Sort?                       = Sort(),
  @SerialName("available_sorts"           ) var availableSorts         : ArrayList<AvailableSorts>   = arrayListOf(),
  @SerialName("filters"                   ) var filters                : ArrayList<Filters>          = arrayListOf(),
  @SerialName("available_filters"         ) var availableFilters       : ArrayList<AvailableFilters> = arrayListOf(),
  @SerialName("pads_info"                 ) var padsInfo               : PadsInfo?                   = PadsInfo()

)