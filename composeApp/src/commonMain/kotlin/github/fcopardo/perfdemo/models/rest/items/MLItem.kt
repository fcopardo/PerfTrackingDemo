package github.fcopardo.perfdemo.models.rest.items

import github.fcopardo.perfdemo.models.rest.AnyToStringSerializer
import github.fcopardo.perfdemo.models.rest.items.Attributes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MLItem (

  @SerialName("id"                               ) var id                           : String?               = null,
  @SerialName("site_id"                          ) var siteId                       : String?               = null,
  @SerialName("title"                            ) var title                        : String?               = null,
  @SerialName("subtitle"                         ) var subtitle                     : String?               = null,
  @SerialName("seller_id"                        ) var sellerId                     : Int?                  = null,
  @SerialName("category_id"                      ) var categoryId                   : String?               = null,
  @SerialName("official_store_id"                ) var officialStoreId              : String?               = null,
  @Serializable(with = AnyToStringSerializer::class)
  @SerialName("price"                            ) var price                        : String?                  = null,
  @Serializable(with = AnyToStringSerializer::class)
  @SerialName("base_price"                       ) var basePrice                    : String?                  = null,
  @Serializable(with = AnyToStringSerializer::class)
  @SerialName("original_price"                   ) var originalPrice                : String?               = null,
  @SerialName("currency_id"                      ) var currencyId                   : String?               = null,
  @SerialName("initial_quantity"                 ) var initialQuantity              : Int?                  = null,
  @SerialName("available_quantity"               ) var availableQuantity            : Int?                  = null,
  @SerialName("sold_quantity"                    ) var soldQuantity                 : Int?                  = null,
  @SerialName("sale_terms"                       ) var saleTerms                    : ArrayList<SaleTerms>  = arrayListOf(),
  @SerialName("buying_mode"                      ) var buyingMode                   : String?               = null,
  @SerialName("listing_type_id"                  ) var listingTypeId                : String?               = null,
  @SerialName("start_time"                       ) var startTime                    : String?               = null,
  @SerialName("stop_time"                        ) var stopTime                     : String?               = null,
  @SerialName("condition"                        ) var condition                    : String?               = null,
  @SerialName("permalink"                        ) var permalink                    : String?               = null,
  @SerialName("thumbnail_id"                     ) var thumbnailId                  : String?               = null,
  @SerialName("thumbnail"                        ) var thumbnail                    : String?               = null,
  @SerialName("secure_thumbnail"                 ) var secureThumbnail              : String?               = null,
  @SerialName("pictures"                         ) var pictures                     : ArrayList<Pictures>   = arrayListOf(),
  @SerialName("video_id"                         ) var videoId                      : String?               = null,
  @SerialName("descriptions"                     ) var descriptions                 : ArrayList<String>     = arrayListOf(),
  @SerialName("accepts_mercadopago"              ) var acceptsMercadopago           : Boolean?              = null,
  @SerialName("non_mercado_pago_payment_methods" ) var nonMercadoPagoPaymentMethods : ArrayList<String>     = arrayListOf(),
  @SerialName("shipping"                         ) var shipping                     : Shipping?             = Shipping(),
  @SerialName("international_delivery_mode"      ) var internationalDeliveryMode    : String?               = null,
  @SerialName("seller_address"                   ) var sellerAddress                : SellerAddress?        = SellerAddress(),
  @SerialName("seller_contact"                   ) var sellerContact                : String?               = null,
  @SerialName("coverage_areas"                   ) var coverageAreas                : ArrayList<String>     = arrayListOf(),
  @SerialName("attributes"                       ) var attributes                   : ArrayList<Attributes> = arrayListOf(),
  @SerialName("warnings"                         ) var warnings                     : ArrayList<String>     = arrayListOf(),
  @SerialName("listing_source"                   ) var listingSource                : String?               = null,
  @SerialName("variations"                       ) var variations                   : ArrayList<String>     = arrayListOf(),
  @SerialName("status"                           ) var status                       : String?               = null,
  @SerialName("sub_status"                       ) var subStatus                    : ArrayList<String>     = arrayListOf(),
  @SerialName("tags"                             ) var tags                         : ArrayList<String>     = arrayListOf(),
  @SerialName("warranty"                         ) var warranty                     : String?               = null,
  @SerialName("catalog_product_id"               ) var catalogProductId             : String?               = null,
  @SerialName("domain_id"                        ) var domainId                     : String?               = null,
  @SerialName("parent_item_id"                   ) var parentItemId                 : String?               = null,
  @SerialName("differential_pricing"             ) var differentialPricing          : String?               = null,
  @SerialName("deal_ids"                         ) var dealIds                      : ArrayList<String>     = arrayListOf(),
  @SerialName("automatic_relist"                 ) var automaticRelist              : Boolean?              = null,
  @SerialName("date_created"                     ) var dateCreated                  : String?               = null,
  @SerialName("last_updated"                     ) var lastUpdated                  : String?               = null,
  @Serializable(with=AnyToStringSerializer::class)
  @SerialName("health"                           ) var health                       : String?               = null,
  @SerialName("catalog_listing"                  ) var catalogListing               : Boolean?              = null,
  @SerialName("channels"                         ) var channels                     : ArrayList<String>     = arrayListOf()

)