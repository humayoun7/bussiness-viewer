package com.humayoun.businessviewer.ui.main

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.humayoun.businessviewer.api.YelpService
import com.humayoun.businessviewer.constant.Constants
import com.humayoun.businessviewer.repository.BusinessRepository

class MainViewModel () : ViewModel() {

    val businessRepository = BusinessRepository(YelpService.create())
    val businessSearchResult = businessRepository.businessesSearchResult
    var location: Location? = null
    var fetchingBusinessData = MutableLiveData<Boolean>()
    // to keep track of page offset
    var PAGE_OFFSET = 0


    fun searchForBusinesses() {
        location?.let {
            fetchingBusinessData.value = true
            businessRepository.getBusinesses(it.latitude, it.longitude, Constants.YelpSerivce.DEFAULT_SEARCH_TERM, PAGE_OFFSET)
        }
    }
}