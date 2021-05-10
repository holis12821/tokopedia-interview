package com.tokopedia.maps.repository

import com.google.gson.GsonBuilder
import com.tokopedia.maps.model.GmapsItem
import com.tokopedia.maps.network.ConfigNetwork
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class MapsRepository {
    /**
     * Higher Ordered Function*/
    fun getData(gmapsRespon: (Array<GmapsItem?>?) -> Unit,
        error: (Throwable) -> Unit) {
        /**
         * define okHttpClient to handle Request*/
        val client = OkHttpClient()
        client.newCall(ConfigNetwork.request())
                .enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        error(e)
                    }
                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body()?.string()
                        val gson = GsonBuilder().create()
                        val result = gson.fromJson(body , Array<GmapsItem?>::class.java)
                        gmapsRespon(result)
                    }
                })
    }
}