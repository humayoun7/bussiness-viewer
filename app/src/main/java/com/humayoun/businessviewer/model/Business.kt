package com.humayoun.businessviewer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Business(
    @Json(name = "name") val name:String,
    @Json(name = "rating") val rating: Double,
    @Json(name = "image_url") val imageUrl: String
)