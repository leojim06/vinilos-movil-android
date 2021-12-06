package com.example.vinilosapp.data.repository

import com.example.vinilosapp.data.api.ApiHelper
import com.example.vinilosapp.data.model.AlbumResponse

class AlbumRepository(private val apiHelper: ApiHelper) {

    suspend fun getAlbums() = apiHelper.getAlbums()

    suspend fun getAlbumDetail(id: String) = apiHelper.getAlbumDetail(id)

    suspend fun createAlbum(album: AlbumResponse) = apiHelper.createAlbum(album)

}