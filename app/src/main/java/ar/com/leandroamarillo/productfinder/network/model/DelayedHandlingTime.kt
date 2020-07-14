package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class DelayedHandlingTime (

   @Json(name = "rate") var rate : Double,
   @Json(name = "value") var value : Int?,
   @Json(name = "period") var period : String

)