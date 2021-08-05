package com.tematikhonov.cinemasearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tematikhonov.cinemasearcher.model.Repository
import com.tematikhonov.cinemasearcher.model.RepositoryImpl

class MainViewModel(private val liveDataObserverMain : MutableLiveData<AppStateMain> = MutableLiveData(),
                    val repository: Repository = RepositoryImpl()) : ViewModel() {

    fun getLiveDataMain() = liveDataObserverMain

    fun getCinemaListNowPlaying() = getDataFromLocalSourceNowPlaying()
    //fun getCinemaListUpcoming() = getDataFromLocalSourceUpcoming()

    private fun getDataFromLocalSourceNowPlaying(){
        Thread{
            Thread.sleep(1000)
            liveDataObserverMain.postValue(AppStateMain.Success(repository.getCinemaListFromLocalSourceNowPlaying()))
        }.start()
    }
}