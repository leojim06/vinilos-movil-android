package com.example.vinilosapp.presentation.vm

import android.util.Log
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
        Log.d("Artist View Model log", "100")
        repository.artistDetails(100)

        /**selectedArtist.value?.let {
            // repository.artistDetails(it.id)
            Log.d("Artist View Model log", "100")
            repository.artistDetails(100)
        }*/
    }
}