package com.example.vinilosapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.vinilosapp.data.model.AlbumResponse
import com.example.vinilosapp.data.repository.AlbumRepository
import com.example.vinilosapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class CreateAlbumViewModel(
    private val albumsRepository: AlbumRepository
) : ViewModel() {

    fun createAlbum(album: AlbumResponse) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            Resource.success(
                data = albumsRepository.createAlbum(album),
                msg = "El álbum " + album.name + " ha sido creado."
            )
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    msg = exception.message ?: "Ha ocurrido un error al crear el álbum!"
                )
            )
        }
    }

    class Factory(val albumsRepository: AlbumRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CreateAlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CreateAlbumViewModel(albumsRepository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}