package com.humayoun.businessviewer.ui.main

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.humayoun.businessviewer.api.YelpService
import com.humayoun.businessviewer.constant.Constants
import com.humayoun.businessviewer.repository.BusinessRepository

class MainViewModel (private val businessRepository: BusinessRepository) : ViewModel() {

    val businessSearchResult = businessRepository.businessesSearchResult
    var location = MutableLiveData<Location>()
    var fetchingBusinessData = MutableLiveData<Boolean>()
    var pageOffset = 0


    fun searchForBusinesses(newLocation: Location?) {
        // reset the pageOffset if it's a new location
        if(location != newLocation) {
            pageOffset = 0
        }

        location.value?.let {
            fetchingBusinessData.value = true
            businessRepository.getBusinesses(it.latitude, it.longitude, Constants.YelpSerivce.DEFAULT_SEARCH_TERM, pageOffset)
        }
    }
}