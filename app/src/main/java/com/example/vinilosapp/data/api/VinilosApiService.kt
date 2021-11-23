package com.example.vinilosapp.data.api

import com.example.vinilosapp.data.model.Album
import com.example.vinilosapp.data.model.AlbumDetailsResults
import com.example.vinilosapp.data.model.AlbumResults
import com.example.vinilosapp.data.model.Collector
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit [VinilosApiService] interface defining api REST requests
 * **/
interface VinilosApiService {

    /**
     * Get [AlbumResults] from searching by album name
     * **/
    @GET(value = "albums")
    suspend fun searchAlbum(): Response<List<Album>>

    /**
     * Get [AlbumDetailsResults] from selected
     * @param album
     * @param artist
     * **/
    @GET("albums")
    suspend fun getAlbumDetails(@Query("album") album: Int): Response<AlbumDetailsResults>

    @GET("collectors")
    suspend fun getCollectors():
            Response<List<Collector>>
}