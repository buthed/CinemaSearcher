package com.tematikhonov.cinemasearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tematikhonov.cinemasearcher.model.Repository
import com.tematikhonov.cinemasearcher.model.RepositoryImpl
import java.lang.Thread.sleep

class CinemaViewModel(private val liveDataObserver :MutableLiveData<AppState> = MutableLiveData(),
                      val repository: Repository = RepositoryImpl()) : ViewModel() {

    fun getLiveData() = liveDataObserver

    fun getCinema() = getDataFromLocalSource()

    fun getDataFromLocalSource(){
        Thread{
            sleep(1000)
            liveDataObserver.postValue(AppState.Success(repository.getCinemaFromLocalSource(1)))
        }.start()
    }


}