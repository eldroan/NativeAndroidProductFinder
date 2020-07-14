package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class Seller (

   @Json(name = "id") var id : Int,
   @Json(name = "permalink") var permalink : String?,
   @Json(name = "registration_date") var registrationDate : String?,
   @Json(name = "car_dealer") var carDealer : Boolean,
   @Json(name = "real_estate_agency") var realEstateAgency : Boolean,
   @Json(name = "tags") var tags : List<String>?,
   @Json(name = "seller_reputation") var sellerReputation : SellerReputation?

)