package com.example.vinilosapp.data

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.VolleyError
import com.example.vinilosapp.data.database.LocalCache
import com.example.vinilosapp.data.model.*
import com.example.vinilosapp.network.NetworkServiceAdapter
import kotlinx.coroutines.*

/**
 * Repository class to enforce data single source of truth
 *
 * **/
class Repository(val application: Application, private val cache: LocalCache) {

    lateinit var albumListData: LiveData<List<Album>>

    val albumDetails = MutableLiveData<AlbumDetails>()

    private val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    /**
     * Await search results from either remote or local database and server to view model
     * **/
    fun getAlbums(value: String): AlbumSearchResults {
        listAlbums(value)
        return AlbumSearchResults(albumListData, networkErrors)
    }

    // Load search results from remote or save to local database
    private fun listAlbums(query: String) = runBlocking {
        async {
            refreshData( {
                cache.removeAlbumsByQuery(query)
                cache.insertAlbum(it)
                albumListData = cache.albumsByQuery(query)
                _networkErrors.postValue("")
            }, { error ->
                albumListData = cache.albumsByQuery(query)
                _networkErrors.postValue(error.toString())
            })
        }.await()
    }

    // Load album details from remote or save to local database
     /*fun albumDetails(idAlbum: Int){
        CoroutineScope(Dispatchers.IO).launch {
            // Fetch cached album details
            val cachedAlbums= cache.albumsByMid(idAlbum)
            CoroutineScope(Dispatchers.Main).launch {
                albumDetails.value = cachedAlbums
            }

            remoteAlbumDetails(idAlbum)
        }
    }
    */


    /**
     * Get albums data from remote
     * @param queryString
     * **/
    @WorkerThread
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbums({
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }


    @WorkerThread
    suspend fun remoteAlbumDetails(albumId: Int, callback: (List<AlbumDetails>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbumDetail(albumId,{
            //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

}