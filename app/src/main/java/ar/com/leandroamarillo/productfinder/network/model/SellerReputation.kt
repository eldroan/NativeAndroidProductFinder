package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class SellerReputation (

   @Json(name = "transactions") var transactions : Transactions,
   @Json(name = "power_seller_status") var powerSellerStatus : String?,
   @Json(name = "metrics") var metrics : Metrics?,
   @Json(name = "level_id") var levelId : String?

)