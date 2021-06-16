package com.tematikhonov.weather.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tematikhonov.cinemasearcher.model.AppState
import com.tematikhonov.cinemasearcher.model.entites.Cinema
import com.tematikhonov.cinemasearcher.model.repository.Repository
import com.tematikhonov.cinemasearcher.model.repository.RepossitoryImpl
import java.lang.Thread.sleep

class MainViewModel() : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repository : Repository = RepossitoryImpl()

    fun getLiveData() = liveDataToObserve

    fun getCinema() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(
                    AppState.Success(repository.getCinemaFromLocalStorage())
            )
        }.start()
    }
}