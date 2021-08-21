package com.tematikhonov.cinemasearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tematikhonov.cinemasearcher.repository.Repository
import com.tematikhonov.cinemasearcher.repository.RepositoryImpl


class CinemaViewModel(val liveDataObserverCinema : MutableLiveData<AppState> = MutableLiveData(),
                      val repository: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveDataCinema() = liveDataObserverCinema

    fun getCinemaFromServer(movie_id: Int) = getDataCinemaFromServer(movie_id)

    fun getDataCinemaFromServer(movie_id: Int) {
        liveDataObserverCinema.value = AppState.Loading
        Thread {
            val data = repository.getCinemaListFromLocalSource()
            liveDataObserverCinema.postValue(AppState.Success(listOf(data)))
        }.start()
    }
}