package com.humayoun.businessviewer.repository

import androidx.lifecycle.MutableLiveData
import com.humayoun.businessviewer.api.YelpService
import com.humayoun.businessviewer.constant.Constants
import com.humayoun.businessviewer.model.BusinessSearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusinessRepository(private val yelpService: YelpService) {

    val businessesSearchResult = MutableLiveData<BusinessSearchResult>()

    fun getBusinesses(latitude: Double, longitue: Double, searchTerm: String, offset: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val searchResults = yelpService.getBusinessSearchResult(latitude, longitue, searchTerm, offset, Constants.YelpSerivce.PAGE_LIMIT)
            businessesSearchResult.postValue(searchResults)
        }
    }

}