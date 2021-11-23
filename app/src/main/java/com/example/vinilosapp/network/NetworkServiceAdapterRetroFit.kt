package com.example.vinilosapp.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class NetworkServiceAdapterRetroFit constructor(context: Context) {
    companion object {
        const val BASE_URL = "https://back-vinyls-populated.herokuapp.com/"
        var instance: NetworkServiceAdapterRetroFit? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapterRetroFit(context).also {
                    instance = it
                }
            }
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }


}