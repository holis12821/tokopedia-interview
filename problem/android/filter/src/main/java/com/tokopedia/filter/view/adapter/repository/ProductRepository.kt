package com.tokopedia.filter.view.adapter.repository

import android.annotation.SuppressLint
import com.tokopedia.filter.view.model.response.ResponseServer
import com.tokopedia.filter.view.network.ConfigNetwork
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductRepository {


    @SuppressLint("CheckResult")
    fun getListDataProduct(responseHandler: (ResponseServer) -> Unit,
                           errorHandler: (Throwable) -> Unit) {
        ConfigNetwork.getRetrofit()
                .getDataProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                        responseHandler(it)
                }, {
                    errorHandler(it)
                })
    }
}