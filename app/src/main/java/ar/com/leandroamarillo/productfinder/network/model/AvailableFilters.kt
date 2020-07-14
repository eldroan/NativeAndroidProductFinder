package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class AvailableFilters (

   @Json(name = "id") var id : String,
   @Json(name = "name") var name : String,
   @Json(name = "type") var type : String,
   @Json(name = "values") var values : List<Values>

)