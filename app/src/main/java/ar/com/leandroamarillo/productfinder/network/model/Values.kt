package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class Values (

   @Json(name = "id") var id : String?,
   @Json(name = "name") var name : String?,
   @Json(name = "results") var results : Int?

)