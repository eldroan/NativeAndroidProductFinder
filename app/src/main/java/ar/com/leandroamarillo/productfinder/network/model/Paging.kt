package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class Paging (

   @Json(name = "total") var total : Int,
   @Json(name = "offset") var offset : Int,
   @Json(name = "limit") var limit : Int,
   @Json(name = "primary_results") var primaryResults : Int

)