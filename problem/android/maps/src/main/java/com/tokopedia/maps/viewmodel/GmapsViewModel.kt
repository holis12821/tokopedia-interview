package com.tokopedia.maps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tokopedia.maps.model.GmapsItem
import com.tokopedia.maps.repository.MapsRepository

class GmapsViewModel: ViewModel() {
    private val repository = MapsRepository()

    private val searchGmaps = MutableLiveData<Array<GmapsItem?>?>()
    val searchGmapsFixed: LiveData<Array<GmapsItem?>?> = searchGmaps

    private val onError = MutableLiveData<Throwable>()
    val onErrorFixed: LiveData<Throwable> = onError

    fun getAllCountries() {
        repository.getData({
             searchGmaps.postValue( it) //run a background thread
            //use postValue, for  example a run thread in the main thread use  value
        }, {
            onError.postValue( it)
        })
    }
}