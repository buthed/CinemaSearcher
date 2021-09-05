package com.tematikhonov.cinemasearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tematikhonov.cinemasearcher.app.App
import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.model.convertCinemaDtoToModel
import com.tematikhonov.cinemasearcher.repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CinemaViewModel(
        val liveDataObserverCinema : MutableLiveData<AppState> = MutableLiveData(),
        val cinemaRepository : CinemaRepositoryImpl = CinemaRepositoryImpl(RemoteDataSource()),
        val historyRepository: HistoryRepositoryImpl = HistoryRepositoryImpl(App.getHistoryDao())) : ViewModel() {

    fun getLiveDataCinema() = liveDataObserverCinema

    fun saveCinemaToDb(cinema: Cinema){
        Thread {
            historyRepository.saveEntity(cinema)
        }.start()
    }

    fun getCinemaFromServer(movie_id: Int) {
        liveDataObserverCinema.postValue(AppState.Loading)
        cinemaRepository.getCinemaFromServer(movie_id, callBack)
    }




    private val callBack = object : Callback<CinemaDTO> {

        override fun onResponse(call: Call<CinemaDTO>, response: Response<CinemaDTO>) {
            val serverResponse: CinemaDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                liveDataObserverCinema.postValue(AppState.Success(convertCinemaDtoToModel(serverResponse)))  // FIXME проверок добавить
            } else {
                //TODO("Ответ нас не устраивает")
            }
        }

        override fun onFailure(call: Call<CinemaDTO>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }
}
