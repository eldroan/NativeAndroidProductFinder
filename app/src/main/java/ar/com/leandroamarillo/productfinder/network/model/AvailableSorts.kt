package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class AvailableSorts (

   @Json(name = "id") var id : String,
   @Json(name = "name") var name : String

)