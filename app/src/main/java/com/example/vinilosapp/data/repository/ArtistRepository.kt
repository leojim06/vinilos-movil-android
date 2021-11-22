package com.example.vinilosapp.data.repository

import com.example.vinilosapp.data.api.ApiHelper

class ArtistRepository(private val apiHelper: ApiHelper) {

    suspend fun getBands() = apiHelper.getBands()
    suspend fun getMusicians() = apiHelper.getMusicians()

    suspend fun getBandstDetail(id: String) = apiHelper.getBandsDetail(id)
    suspend fun getMusicianstDetail(id: String) = apiHelper.getMusiciansDetail(id)
}