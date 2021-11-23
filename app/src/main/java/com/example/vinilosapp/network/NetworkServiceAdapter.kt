package com.example.vinilosapp.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilosapp.data.model.Collector
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object {
        const val BASE_URL = "https://back-vinyls-populated.herokuapp.com/"
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

    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont ->
        requestQueue.add(
            getRequest("collectors",
                { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Collector>()
                    for (i in 0 until resp.length()) { //inicializado como variable de retorno
                        val item = resp.getJSONObject(i)
                        val collector = Collector(
                            collectorId = item.getInt("id"),
                            name = item.getString("name"),
                            telephone = item.getString("telephone"),
                            email = item.getString("email")
                        )
                        list.add(collector) //se agrega a medida que se procesa la respuesta
                    }
                    cont.resume(list)
                },
                {
                    cont.resumeWithException(it)
                })
        )
    }

    private fun getRequest(
        path: String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }

    private fun postRequest(
        path: String,
        body: JSONObject,
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        return JsonObjectRequest(
            Request.Method.POST,
            BASE_URL + path,
            body,
            responseListener,
            errorListener
        )
    }

    private fun putRequest(
        path: String,
        body: JSONObject,
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        return JsonObjectRequest(
            Request.Method.PUT,
            BASE_URL + path,
            body,
            responseListener,
            errorListener
        )
    }
}