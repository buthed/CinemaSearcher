package com.tematikhonov.cinemasearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tematikhonov.cinemasearcher.model.Repository
import com.tematikhonov.cinemasearcher.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val liveDataObserverMain : MutableLiveData<AppStateMain> = MutableLiveData(),
                    val repository: Repository = RepositoryImpl()) : ViewModel() {

    fun getLiveDataMain() = liveDataObserverMain

    fun getCinemaListNowPlaying() = getDataFromLocalSourceNowPlaying()
    fun getCinemaListUpcoming() = getDataFromLocalSourceUpcoming()

    private fun getDataFromLocalSourceNowPlaying(){
    Thread {
        with(liveDataObserverMain) {
                postValue(AppStateMain.Loading)
                sleep(4000)
        }
    }.start()
    }

    private fun getDataFromLocalSourceUpcoming(){
    Thread {
        with(liveDataObserverMain) {
            postValue(AppStateMain.Loading)
            sleep(4000)
        }
    }.start()
}
}