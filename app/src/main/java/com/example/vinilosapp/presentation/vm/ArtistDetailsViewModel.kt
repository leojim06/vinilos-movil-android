package com.example.vinilosapp.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinilosapp.data.Repository
import com.example.vinilosapp.data.model.ArtistDetails

class ArtistDetailsViewModel(private val repository: Repository): ViewModel() {

    val selectedArtist = MutableLiveData<ArtistDetails>()
    val artistsDetails = repository.artistDetails
    var networkErrors = repository.networkErrors

    fun setSelectedArtist(artistDetails: ArtistDetails){
        selectedArtist.value = artistDetails
    }

    fun getArtistDetails() {
        selectedArtist.value?.let {
            // repository.artistDetails(it.id)
            repository.artistDetails(100)
        }
    }
}