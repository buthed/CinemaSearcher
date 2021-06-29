package com.tematikhonov.cinemasearcher.framework.ui.details_fragment

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tematikhonov.cinemasearcher.model.AppState
import com.tematikhonov.cinemasearcher.model.repository.Repository

class DetailsViewModel(private val repository : Repository) : ViewModel(), LifecycleObserver {
    internal val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(movie_id: Int) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val data = repository.getCinemaFromServer(movie_id)
            liveDataToObserve.postValue(AppState.Success(listOf(data)))
        }.start()
    }
}