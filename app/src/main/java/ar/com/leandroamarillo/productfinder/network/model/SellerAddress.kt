package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class SellerAddress (

   @Json(name = "id") var id : String,
   @Json(name = "comment") var comment : String,
   @Json(name = "address_line") var addressLine : String,
   @Json(name = "zip_code") var zipCode : String,
   @Json(name = "country") var country : Country,
   @Json(name = "state") var state : State,
   @Json(name = "city") var city : City,
   @Json(name = "latitude") var latitude : String,
   @Json(name = "longitude") var longitude : String

)