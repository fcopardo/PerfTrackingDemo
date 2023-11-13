package github.fcopardo.perfdemo.models.rest.search

import github.fcopardo.perfdemo.models.rest.AnyToStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

@Serializable
data class Results (

    @SerialName("id"                  ) var id                 : String?               = null,
    @SerialName("title"               ) var title              : String?               = null,
    @SerialName("condition"           ) var condition          : String?               = null,
    @SerialName("thumbnail_id"        ) var thumbnailId        : String?               = null,
    @SerialName("catalog_product_id"  ) var catalogProductId   : String?               = null,
    @SerialName("listing_type_id"     ) var listingTypeId      : String?               = null,
    @SerialName("permalink"           ) var permalink          : String?               = null,
    @SerialName("buying_mode"         ) var buyingMode         : String?               = null,
    @SerialName("site_id"             ) var siteId             : String?               = null,
    @SerialName("category_id"         ) var categoryId         : String?               = null,
    @SerialName("domain_id"           ) var domainId           : String?               = null,
    @SerialName("thumbnail"           ) var thumbnail          : String?               = null,
    @SerialName("currency_id"         ) var currencyId         : String?               = null,
    @SerialName("order_backend"       ) var orderBackend       : Int?                  = null,
    @SerialName("price"               ) var price              : Int?                  = null,
    @SerialName("original_price"      ) var originalPrice      : Int?                  = null,
    @SerialName("sale_price"          ) var salePrice          : String?               = null,
    @SerialName("sold_quantity"       ) var soldQuantity       : Int?                  = null,
    @SerialName("available_quantity"  ) var availableQuantity  : Int?                  = null,
    @Serializable(with=AnyToStringSerializer::class)
    @SerialName("official_store_id"   ) var officialStoreId    : String?               = null,
    @SerialName("use_thumbnail_id"    ) var useThumbnailId     : Boolean?              = null,
    @SerialName("accepts_mercadopago" ) var acceptsMercadopago : Boolean?              = null,
    @SerialName("tags"                ) var tags               : ArrayList<String>     = arrayListOf(),
    @SerialName("shipping"            ) var shipping           : Shipping?             = Shipping(),
    @SerialName("stop_time"           ) var stopTime           : String?               = null,
    @SerialName("seller"              ) var seller             : Seller?               = Seller(),
    @SerialName("seller_address"      ) var sellerAddress      : SellerAddress?        = SellerAddress(),
    @SerialName("address"             ) var address            : Address?              = Address(),
    @SerialName("attributes"          ) var attributes         : ArrayList<Attributes> = arrayListOf(),
    @SerialName("installments"        ) var installments       : Installments?         = Installments(),
    @SerialName("winner_item_id"      ) var winnerItemId       : String?               = null,
    @SerialName("catalog_listing"     ) var catalogListing     : Boolean?              = null,
    @SerialName("discounts"           ) var discounts          : String?               = null,
    @SerialName("promotions"          ) var promotions         : ArrayList<String>     = arrayListOf(),
    @SerialName("inventory_id"        ) var inventoryId        : String?               = null
)