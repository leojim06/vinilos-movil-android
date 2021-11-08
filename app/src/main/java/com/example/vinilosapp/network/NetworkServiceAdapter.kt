package com.example.vinilosapp.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilosapp.data.model.Album
import com.example.vinilosapp.data.model.AlbumDetails
import org.json.JSONArray
import org.json.JSONObject

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://back-vinyls-populated.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getAlbums(onComplete:(resp:List<Album>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Album(idAlbum = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description")))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }

   fun getAlbumDetail(albumId:Int, onComplete:(resp:List<AlbumDetails>)->Unit, onError: (error:VolleyError)->Unit) {
       requestQueue.add(getRequest("albums/$albumId",
           { response ->
               val resp = JSONObject(response)
               val list = mutableListOf<AlbumDetails>()
               var item:JSONObject? = null
            //   for (i in 0 until resp.length()) {
              //     item = resp.getJSONObject(i)
                   Log.d("Response", resp.toString())
                   list.add(0, AlbumDetails(idAlbum = albumId, name = resp.getString("name").toString(), cover = resp.getString("cover").toString(),  releaseDate = resp.getString("releaseDate").toString(), description = resp.getString("description").toString(), genre = resp.getString("genre").toString(),  recordLabel = resp.getString("recordLabel").toString()))
             //  }
               Log.d("Lista", list[0].description.toString())
               onComplete(list)
           },
           {
               onError(it)
           }))
   }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }

}