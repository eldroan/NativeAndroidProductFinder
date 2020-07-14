package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json


data class SearchResponse (

   @Json(name = "site_id") var siteId : String,
   @Json(name = "query") var query : String,
   @Json(name = "paging") var paging : Paging,
   @Json(name = "results") var results : List<Results>,
   @Json(name = "secondary_results") var secondaryResults : List<String>,
   @Json(name = "related_results") var relatedResults : List<String>,
   @Json(name = "sort") var sort : Sort,
   @Json(name = "available_sorts") var availableSorts : List<AvailableSorts>,
   @Json(name = "filters") var filters : List<Filters>,
   @Json(name = "available_filters") var availableFilters : List<AvailableFilters>

)