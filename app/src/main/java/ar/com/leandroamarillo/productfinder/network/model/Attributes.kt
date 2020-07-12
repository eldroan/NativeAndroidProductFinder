package ar.com.leandroamarillo.productfinder.network.model

import com.squareup.moshi.Json

data class Attributes(

    @Json(name = "name") var name: String,
    @Json(name = "value_name") var valueName: String?,
    @Json(name = "values") var values: List<Values>?,
    @Json(name = "attribute_group_name") var attributeGroupName: String,
    @Json(name = "id") var id: String,
    @Json(name = "value_id") var valueId: String?,
    @Json(name = "attribute_group_id") var attributeGroupId: String,
    @Json(name = "source") var source: Long?

)