package com.example.vinilosapp.data.repository

import com.example.vinilosapp.data.api.ApiHelper

class CollectorRepository(private val apiHelper: ApiHelper) {

    suspend fun getCollectors() = apiHelper.getCollectors()
    suspend fun getCollectorDetail(id: String) = apiHelper.getCollectorDetail(id)
}