package com.example.vinilosapp.data

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vinilosapp.data.api.API_KEY
import com.example.vinilosapp.data.api.LastFmApiService
import com.example.vinilosapp.data.database.LocalCache
import com.example.vinilosapp.data.model.*
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import kotlin.math.log

/**
 * Repository class to enforce data single source of truth
 *
 * **/
class Repository(private val service :LastFmApiService, private val cache: LocalCache) {

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
            remoteAlbumSearch(query, {
                cache.removeAlbumsByQuery(query)
                cache.insertAlbum(it)
                albumListData = cache.albumsByQuery(query)
                _networkErrors.postValue("")
            }, { error ->
                albumListData = cache.albumsByQuery(query)
                _networkErrors.postValue(error)
            })
        }.await()
    }

    // Load album details from remote or save to local database
     fun albumDetails(album: Int){
        CoroutineScope(Dispatchers.IO).launch {
            // Fetch cached album details
            val cachedAlbums= cache.albumsByMid(album)
            CoroutineScope(Dispatchers.Main).launch {
                albumDetails.value = cachedAlbums
            }

            if (cachedAlbums == null){
                remoteAlbumDetails(album)
            }
        }
    }


    /**
     * Get albums data from remote
     * @param queryString
     * **/
    @WorkerThread
    suspend fun remoteAlbumSearch(queryString: String,
                                  onSuccess: (results: List<Album>) ->Unit,
                                  onError: (error: String) -> Unit){
        try {
            val response  = service.searchAlbum()
            Log.d("Response111", response.toString())
            //val remoteData = response.body()?.results?.albummatches?.album ?: emptyList()

            val resp = JSONArray(response.body())

            val list = mutableListOf<Album>()
            Log.d("Response222", resp.length().toString())

            for (i in 0 until resp.length()) {
                val item = response.body()?.get(i)
                if (item != null) {
                    list.add(i, Album(id = item.id,name = item.name, cover = item.cover, recordLabel = item.recordLabel, releaseDate = item.releaseDate, genre = item.genre, description = item.description))
                }
            }

            onSuccess(list)
        }catch (exception : Exception){
            exception.message?.let {
                onError(it)
            }
        }

    }


    @WorkerThread
    suspend fun remoteAlbumDetails(album: Int){
        try {
            val response  = service.getAlbumDetails(album)
            val remoteDetailsData = response.body()?.album
            remoteDetailsData?.let {
                cache.insertAlbumDetails(it)
                CoroutineScope(Dispatchers.Main).launch {
                    albumDetails.value = it
                }
            }
            _networkErrors.postValue("")
        }catch (exception : Exception){
            exception.message?.let {
                CoroutineScope(Dispatchers.Main).launch {
                    _networkErrors.value = it
                }
            }
        }

    }

}