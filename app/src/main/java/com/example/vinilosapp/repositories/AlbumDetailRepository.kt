package com.example.vinilosapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilosapp.models.Album
import com.example.vinilosapp.network.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application){
    fun refreshData(albumId: Int, callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbumDetail(albumId,{
            //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}