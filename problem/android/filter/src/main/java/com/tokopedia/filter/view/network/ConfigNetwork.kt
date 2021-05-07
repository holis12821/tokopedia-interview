package com.tokopedia.filter.view.network

import com.tokopedia.filter.view.network.baseurl.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigNetwork {
    fun getRetrofit(): ProductService {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        //return services to handle
        return retrofit.create(ProductService::class.java)
    }
}