package com.example.vinilosapp.data.api


class ApiHelper(private val apiService: ApiService) {

    suspend fun getAlbums() = apiService.getAlbums()
    suspend fun getAlbumDetail(id: String) = apiService.getAlbumDetail(id)
    suspend fun getBands() = apiService.getBands()
    suspend fun getMusicians() = apiService.getMusicians()
    suspend fun getBandsDetail(id: String) = apiService.getBandsDetail(id)
    suspend fun getMusiciansDetail(id: String) = apiService.getMusiciansDetail(id)
    suspend fun getCollectors() = apiService.getCollectors()

}