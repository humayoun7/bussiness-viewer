package com.humayoun.businessviewer.ui.main

import androidx.lifecycle.ViewModel
import com.humayoun.businessviewer.api.YelpService
import com.humayoun.businessviewer.repository.BusinessRepository

class MainViewModel () : ViewModel() {

    val businessRepository = BusinessRepository(YelpService.create())
    val businessSearchResult = businessRepository.businessesSearchResult
    // to keep track of page offset
    var PAGE_OFFSET = 0


    fun searchForBusinesses() {
        businessRepository.getBusinesses(PAGE_OFFSET)
    }
}