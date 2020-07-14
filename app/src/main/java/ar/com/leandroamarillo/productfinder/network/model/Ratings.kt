package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class Ratings (

   @Json(name = "negative") var negative : Float,
   @Json(name = "positive") var positive : Float,
   @Json(name = "neutral") var neutral : Float

)