package com.humayoun.businessviewer.repository

import androidx.lifecycle.MutableLiveData
import com.humayoun.businessviewer.api.YelpService
import com.humayoun.businessviewer.model.BusinessSearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusinessRepository(private val yelpService: YelpService) {

    val businessesSearchResult = MutableLiveData<BusinessSearchResult>()

    fun getBusinesses() {
        CoroutineScope(Dispatchers.IO).launch {
            val searchResults = yelpService.getBusinessSearchResult("Los Angeles", 0, 50)
            businessesSearchResult.postValue(searchResults)
        }
    }

}