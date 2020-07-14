package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class Metrics (

   @Json(name = "claims") var claims : Claims,
   @Json(name = "delayed_handling_time") var delayedHandlingTime : DelayedHandlingTime,
   @Json(name = "sales") var sales : Sales,
   @Json(name = "cancellations") var cancellations : Cancellations?

)