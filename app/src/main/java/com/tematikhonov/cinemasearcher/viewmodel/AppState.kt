package com.tematikhonov.cinemasearcher.viewmodel

import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.CinemaDTO

sealed class AppState {
    data class Success(val dataCinema: List<Cinema>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

sealed class AppStateMain{
    data class Success(val dataCinemaNowPlaying: List<CinemaDTO>, val dataCinemaUpcoming: List<CinemaDTO>) : AppStateMain()
    data class Error(val error: Throwable) : AppStateMain()
    object Loading : AppStateMain()
}
