package com.example.vinilosapp.application

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.vinilosapp.Utilities.PrefsHelper
import com.example.vinilosapp.data.Repository
import com.example.vinilosapp.data.api.RetrofitInstance
import com.example.vinilosapp.data.database.VinilosUniandesDatabase
import com.example.vinilosapp.data.database.LocalCache
import com.example.vinilosapp.presentation.factory.ViewModelFactory
import java.util.concurrent.Executors

/**
 * Base application class for app initialisation and DI
 * **/
class VinilosUniandesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        PrefsHelper.init(appContext)
    }


    companion object{
        lateinit var appContext : Context

        /**
         * Creates an instance of [LocalCache] based on the database DAO.
         */
         fun provideCache(context: Context): LocalCache {
            val database = VinilosUniandesDatabase.getInstance(context)
            return LocalCache(database.albumsDao(), Executors.newSingleThreadExecutor())
        }

        /**
         * Creates an instance of [Repository] based on the [RetrofitInstance] and a
         * [LocalCache]
         */
         fun provideRepository(context: Context): Repository {
            return Repository(RetrofitInstance.LAST_API_SERVICE, provideCache(context))
        }

        /**
         * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
         * [ViewModel] objects.
         */
        fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
            return ViewModelFactory(provideRepository(context))
        }

    }

}