package com.example.vinilosapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilosapp.data.api.ApiHelper
import com.example.vinilosapp.data.repository.AlbumRepository
import com.example.vinilosapp.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(AlbumRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Clase desconocida")
    }

}