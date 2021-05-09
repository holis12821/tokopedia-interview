package com.tokopedia.filter.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tokopedia.filter.view.repository.ProductRepository
import com.tokopedia.filter.view.model.response.ResponseServer

class ProductListViewModel: ViewModel() {
    private val repository = ProductRepository()

    /*encapsulate data*/
    /*implement on Live data*/
    private val onSuccess = MutableLiveData<ResponseServer>()
    val onSuccessFix: LiveData<ResponseServer> = onSuccess

    private val onError = MutableLiveData<Throwable>()
    val onErrorFix: LiveData<Throwable> = onError

    fun getData() {
        repository.getListDataProduct({
          if (it.statusCode ==  200) {
              onSuccess.value = it
          }
        }, {
            onError.value = it
        })
    }
}