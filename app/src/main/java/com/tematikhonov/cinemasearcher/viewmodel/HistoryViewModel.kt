package com.tematikhonov.cinemasearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tematikhonov.cinemasearcher.app.App
import com.tematikhonov.cinemasearcher.repository.HistoryRepositoryImpl

class HistoryViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: HistoryRepositoryImpl = HistoryRepositoryImpl(App.getHistoryDao())) : ViewModel() {
    fun getLiveData() = liveDataObserver


    fun getAllHistory(){
        liveDataObserver.value = AppState.Loading
        liveDataObserver.value = AppState.Success(historyRepository.getAllHistory())
    }

    fun deleteByName(name:String) {
        //liveDataObserver.postValue(AppState.Loading)
        historyRepository.deleteEntityByName(name)
        liveDataObserver.value = AppState.Success(historyRepository.getAllHistory())
    }
}