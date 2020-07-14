package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class Installments (

   @Json(name = "quantity") var quantity : Int,
   @Json(name = "amount") var amount : Double,
   @Json(name = "rate") var rate : Double,
   @Json(name = "currency_id") var currencyId : String?

)