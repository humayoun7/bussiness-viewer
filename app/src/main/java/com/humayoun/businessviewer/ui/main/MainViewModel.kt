package com.humayoun.businessviewer.ui.main

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.humayoun.businessviewer.api.YelpService
import com.humayoun.businessviewer.constant.Constants
import com.humayoun.businessviewer.repository.BusinessRepository

/**
 * ViewModel for MainFragment and MainActivity
 * */

class MainViewModel (private val businessRepository: BusinessRepository) : ViewModel() {

    val businessSearchResult = businessRepository.businessesSearchResult
    var location = MutableLiveData<Location>()
    var fetchingBusinessData = MutableLiveData<Boolean>()
    var pageOffset = 0
    var currentlySearching = MutableLiveData<String>()


    fun searchForBusinesses(newLocation: Location?, searchFor: String) {
        // reset the pageOffset if it's a new location
        if(location != newLocation) {
            pageOffset = 0
        }

        currentlySearching.value = searchFor

        location.value?.let {
            fetchingBusinessData.value = true
            businessRepository.getBusinesses(it.latitude, it.longitude, searchFor, pageOffset)
        }
    }
}