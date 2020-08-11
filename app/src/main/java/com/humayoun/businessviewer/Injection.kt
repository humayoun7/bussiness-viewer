package com.humayoun.businessviewer

import androidx.lifecycle.ViewModelProvider
import com.humayoun.businessviewer.api.YelpService
import com.humayoun.businessviewer.repository.BusinessRepository
import com.humayoun.businessviewer.ui.main.ViewModelFactory

/**
  * Class that handles object creation.
  * Like this, objects can be passed as parameters in the constructors and then replaced for
  * testing, where needed.
  **/
object Injection {

    private fun provideBusinessRepository(): BusinessRepository {
        return BusinessRepository(YelpService.create())
    }

     // Provides the [ViewModelProvider.Factory] that is then used to get a reference to [ViewModel] objects.
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideBusinessRepository())
    }
}
