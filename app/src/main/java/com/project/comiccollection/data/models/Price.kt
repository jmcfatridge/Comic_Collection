package com.project.comiccollection.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Price(
    @Json(name = "price")
    val price: Int?,
    @Json(name = "type")
    val type: String?
)