package com.example.vinilosapp.presentation.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinilosapp.data.Repository
import com.example.vinilosapp.data.model.Album

class AlbumDetailsViewModel(private val repository: Repository, albumId: Int): ViewModel() {

    // Shared album item between details and search fragment
    val selectedAlbum = MutableLiveData<Album>()

    val albumsDetails = repository.albumDetails

    var networkErrors = repository.networkErrors

    val id:Int = albumId
    /**
     * Set value to Shared album [Album] used in Details Fragment
     * **/
    fun setSelectedAlbum(album : Album){
        selectedAlbum.value = album
    }


    /**
     * Load album details from [Repository]
     * **/
    suspend fun getAlbumDetails(){

            repository.remoteAlbumDetails(id, {
                Log.d("Respuesta", it.toString())

            },{
                Log.d("Error", it.toString())

            })

    }
}