package com.humayoun.businessviewer.ui.main

import androidx.lifecycle.ViewModel
import com.humayoun.businessviewer.api.YelpService
import com.humayoun.businessviewer.repository.BusinessRepository

class MainViewModel () : ViewModel() {

    val businessRepository = BusinessRepository(YelpService.create())
    val businessSearchResult = businessRepository.businessesSearchResult

    init {
        businessRepository.getBusinesses()
    }
}