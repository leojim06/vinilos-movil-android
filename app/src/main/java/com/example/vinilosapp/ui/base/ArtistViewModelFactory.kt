package com.example.vinilosapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilosapp.data.api.ApiHelper
import com.example.vinilosapp.data.repository.ArtistRepository
import com.example.vinilosapp.ui.main.viewmodel.ArtistViewModel

class ArtistViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
            return ArtistViewModel(ArtistRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Clase desconocida")
    }
}