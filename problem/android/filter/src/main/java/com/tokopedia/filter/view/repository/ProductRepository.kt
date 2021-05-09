package com.tokopedia.filter.view.repository

import com.tokopedia.filter.view.model.response.ResponseServer
import com.tokopedia.filter.view.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductRepository {

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