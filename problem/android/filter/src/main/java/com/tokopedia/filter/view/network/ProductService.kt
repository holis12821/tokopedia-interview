package com.tokopedia.filter.view.network

import com.tokopedia.filter.view.model.response.ResponseServer
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("api/json/get/EkROGxTwq")
    fun getDataProduct(): Flowable<ResponseServer>
}