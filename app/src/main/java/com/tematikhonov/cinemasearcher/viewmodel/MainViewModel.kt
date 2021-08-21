package com.tematikhonov.cinemasearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tematikhonov.cinemasearcher.repository.Repository
import com.tematikhonov.cinemasearcher.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val liveDataObserverMain : MutableLiveData<AppStateMain> = MutableLiveData(),
                    val repository: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveDataMain() = liveDataObserverMain

    fun getCinemaListNowPlaying() = getDataFromLocalSourceNowPlaying()
    fun getCinemaListUpcoming() = getDataFromLocalSourceUpcoming()

    private fun getDataFromLocalSourceNowPlaying(){
    Thread {
        with(liveDataObserverMain) {
                postValue(AppStateMain.Loading)
                sleep(2000)
        }
    }.start()
    }

    private fun getDataFromLocalSourceUpcoming(){
    Thread {
        with(liveDataObserverMain) {
            postValue(AppStateMain.Loading)
            sleep(2000)
        }
    }.start()
    }
}