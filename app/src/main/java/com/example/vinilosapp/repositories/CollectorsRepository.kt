package com.example.vinilosapp.repositories

import android.app.Application
import com.example.vinilosapp.data.api.NetworkServiceAdapter
import com.example.vinilosapp.data.model.Collector

class CollectorsRepository(val application: Application) {
    suspend fun refreshData(): List<Collector> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getCollectors()
    }
}