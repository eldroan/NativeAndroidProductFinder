package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class Shipping (

   @Json(name = "free_shipping") var freeShipping : Boolean,
   @Json(name = "mode") var mode : String,
   @Json(name = "tags") var tags : List<String>,
   @Json(name = "logistic_type") var logisticType : String?,
   @Json(name = "store_pick_up") var storePickUp : Boolean

)