package com.example.vinilosapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilosapp.data.api.ApiHelper
import com.example.vinilosapp.data.repository.CollectorRepository
import com.example.vinilosapp.ui.main.viewmodel.CollectorViewModel

class CollectorViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CollectorViewModel::class.java)) {
            return CollectorViewModel(CollectorRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Clase desconocida")
    }

}