package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class Results (

   @Json(name = "id") var id : String,
   @Json(name = "site_id") var siteId : String,
   @Json(name = "title") var title : String,
   @Json(name = "seller") var seller : Seller,
   @Json(name = "price") var price : Double?,
   @Json(name = "currency_id") var currencyId : String?,
   @Json(name = "available_quantity") var availableQuantity : Int,
   @Json(name = "sold_quantity") var soldQuantity : Int,
   @Json(name = "buying_mode") var buyingMode : String,
   @Json(name = "listing_type_id") var listingTypeId : String,
   @Json(name = "stop_time") var stopTime : String,
   @Json(name = "condition") var condition : String?,
   @Json(name = "permalink") var permalink : String,
   @Json(name = "thumbnail") var thumbnail : String,
   @Json(name = "accepts_mercadopago") var acceptsMercadopago : Boolean,
   @Json(name = "installments") var installments : Installments?,
   @Json(name = "address") var address : Address,
   @Json(name = "shipping") var shipping : Shipping,
   @Json(name = "seller_address") var sellerAddress : SellerAddress,
   @Json(name = "attributes") var attributes : List<Attributes>,
   @Json(name = "original_price") var originalPrice : String?,
   @Json(name = "category_id") var categoryId : String,
   @Json(name = "official_store_id") var officialStoreId : String?,
   @Json(name = "domain_id") var domainId : String?,
   @Json(name = "catalog_product_id") var catalogProductId : String?,
   @Json(name = "tags") var tags : List<String>,
   @Json(name = "catalog_listing") var catalogListing : Boolean?

)