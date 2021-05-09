package com.tokopedia.filter.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tokopedia.filter.view.adapter.repository.ProductRepository
import com.tokopedia.filter.view.model.response.ResponseServer

class ProductListViewModel: ViewModel() {
    private val repository = ProductRepository()

    /*encapsulate data*/
    /*implement on Live data*/
    private val onSuccess = MutableLiveData<Int>()
    val onSuccessFix: LiveData<Int> = onSuccess

    private val onError = MutableLiveData<String>()
    val onErrorFix: LiveData<String> = onError

    private val showData = MutableLiveData<ResponseServer>()
    val showDataFix: LiveData<ResponseServer> = showData

    fun getData() {
        repository.getListDataProduct({
            onSuccess.value = it.statusCode
        }, {
            onError.value = it.message
        })
    }
}