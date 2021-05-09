package com.tokopedia.maps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tokopedia.maps.model.Gmaps
import com.tokopedia.maps.repository.MapsRepository

class GmapsViewModel: ViewModel() {
    private val repository = MapsRepository()

    private val searchGmaps = MutableLiveData<Array<Gmaps?>?>()
    val searchGmapsFixed: LiveData<Array<Gmaps?>?> = searchGmaps

    private val onError = MutableLiveData<Throwable>()
    val onErrorFixed: LiveData<Throwable> = onError

    fun getAllCountries() {
        repository.getData({
             searchGmaps.value = it
        }, {
            onError.value = it
        })
    }
}