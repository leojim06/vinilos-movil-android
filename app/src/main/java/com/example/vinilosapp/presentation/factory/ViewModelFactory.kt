package com.example.vinilosapp.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilosapp.data.Repository
import com.example.vinilosapp.presentation.vm.AlbumDetailsViewModel
import com.example.vinilosapp.presentation.vm.SearchViewModel

class ViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(AlbumDetailsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AlbumDetailsViewModel(repository, 3) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}