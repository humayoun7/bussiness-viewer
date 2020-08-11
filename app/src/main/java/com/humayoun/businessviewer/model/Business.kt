package com.humayoun.businessviewer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Representing a business, more fields can be added based on requirements
 * */

@JsonClass(generateAdapter = true)
data class Business(
    @Json(name = "name") val name:String,
    @Json(name = "rating") val rating: Double,
    @Json(name = "image_url") val imageUrl: String
)