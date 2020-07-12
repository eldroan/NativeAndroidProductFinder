package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class Sales (

   @Json(name = "period") var period : String,
   @Json(name = "completed") var completed : Int

)