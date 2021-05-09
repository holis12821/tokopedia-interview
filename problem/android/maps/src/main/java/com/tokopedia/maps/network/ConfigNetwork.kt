package com.tokopedia.maps.network

import com.tokopedia.maps.network.baseurl.Constants
import okhttp3.Request

object ConfigNetwork {
    fun request(): Request {
        return Request.Builder()
                .url(Constants.BASE_URL)
                .get()
                .addHeader("x-rapidapi-key", Constants.RAPIDAPI_API_KEY)
                .addHeader("x-rapidapi-host", Constants.RAPIDAPI_HOST)
                .build()
    }
}