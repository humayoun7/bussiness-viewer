package com.humayoun.businessviewer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * SearchResult return from the Yelp API
 * */

@JsonClass(generateAdapter = true)
data class BusinessSearchResult (
    @Json(name = "total") val total: Int,
    @Json(name = "businesses") val businesses: List<Business>
)