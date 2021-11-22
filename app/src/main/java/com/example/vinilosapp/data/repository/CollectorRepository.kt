package com.example.vinilosapp.data.repository

import com.example.vinilosapp.data.api.ApiHelper

class CollectorRepository(private val apiHelper: ApiHelper) {

    suspend fun getCollectors() = apiHelper.getCollectors()
}