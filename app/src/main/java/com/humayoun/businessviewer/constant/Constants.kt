package com.humayoun.businessviewer.constant

/**
 * keeping constants here
 * AUTHORIZATION_HEADER_VALUE should be encrypted and perhaps stored at server
 * but for the sake of simplicity for this project/challenge just storing it here
 * */

object Constants {
    object YelpService {
        const val BASE_URL = "https://api.yelp.com/"
        const val AUTHORIZATION_HEADER = "Authorization"
        const val AUTHORIZATION_HEADER_VALUE = "Bearer itoMaM6DJBtqD54BHSZQY9WdWR5xI_CnpZdxa3SG5i7N0M37VK1HklDDF4ifYh8SI-P2kI_mRj5KRSF4_FhTUAkEw322L8L8RY6bF1UB8jFx3TOR0-wW6Tk0KftNXXYx"
        const val PAGE_LIMIT = 50
        const val RELOAD_WHEN_REMAINING_COUNT = 15
        const val DEFAULT_SEARCH_TERM = "Restaurants"
    }

    const val LOCATION_PERMISSION_ID = 0
}