package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class State (

   @Json(name = "id") var id : String,
   @Json(name = "name") var name : String

)