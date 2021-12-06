package com.example.vinilosapp.ui.main.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.vinilosapp.data.model.AlbumResponse
import com.example.vinilosapp.data.repository.AlbumRepository
import com.example.vinilosapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val AlbumRepository: AlbumRepository) : ViewModel() {
    fun getAlbums() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = AlbumRepository.getAlbums()))
        } catch (exception: Exception) {
            println(exception.localizedMessage)
            emit(Resource.error(data = null, msg = exception.message ?: "Un error ha ocurrido!"))
        }
    }

    fun getAlbumDetail(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = AlbumRepository.getAlbumDetail(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Un error ha ocurrido!"))
        }
    }
}