package com.example.vinilosapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "9607b9595da1882f043c572ea9329cb7"
class RetrofitInstance {


    /**
     * Retrofit singleton instance using [GsonConverterFactory]
     * **/
    companion object{

        private const val BASE_URL = "https://back-vinyls-populated.herokuapp.com/"
        /**
         *Initialise retrofit instance to be used for http requests
         * **/
        private fun retrofit() : Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        /**
         * [VinilosApiService] to call http Api methods
         * **/
        val LAST_API_SERVICE: VinilosApiService by lazy {
            retrofit().create(VinilosApiService::class.java)
        }
    }



}