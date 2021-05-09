package com.tokopedia.filter.view.network

import com.tokopedia.filter.view.model.response.ResponseServer
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ProductService {
    @GET("api/json/get/EkROGxTwq")
    fun getDataProduct(): Flowable<ResponseServer>
}