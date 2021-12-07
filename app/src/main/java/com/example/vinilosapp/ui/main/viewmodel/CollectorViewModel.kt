package com.example.vinilosapp.ui.main.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.vinilosapp.data.repository.CollectorRepository
import com.example.vinilosapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class CollectorViewModel(private val CollectorRepository: CollectorRepository) : ViewModel() {

    fun getColelctors() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = CollectorRepository.getCollectors()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Un error ha ocurrido!"))
        }
    }

    fun getCollectorDetail(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = CollectorRepository.getCollectorDetail(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Un error ha ocurrido!"))
        }
    }
}