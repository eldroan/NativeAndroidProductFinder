package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class Transactions (

   @Json(name = "total") var total : Int,
   @Json(name = "canceled") var canceled : Int,
   @Json(name = "period") var period : String,
   @Json(name = "ratings") var ratings : Ratings,
   @Json(name = "completed") var completed : Int

)