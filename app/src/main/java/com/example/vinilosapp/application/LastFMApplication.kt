package com.example.vinilosapp.application

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.example.vinilosapp.Utilities.PrefsHelper
import com.example.vinilosapp.data.Repository
import com.example.vinilosapp.data.database.LastFmDatabase
import com.example.vinilosapp.data.database.LocalCache
import com.example.vinilosapp.presentation.factory.ViewModelFactory
import java.util.concurrent.Executors

/**
 * Base application class for app initialisation and DI
 * **/
class LastFMApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        PrefsHelper.init(appContext)
    }


    companion object{
        lateinit var appContext : Context
        lateinit var application : Application

        /**
         * Creates an instance of [LocalCache] based on the database DAO.
         */
         fun provideCache(context: Context): LocalCache {
            val database = LastFmDatabase.getInstance(context)
            return LocalCache(database.albumsDao(), Executors.newSingleThreadExecutor())
        }





    }

}