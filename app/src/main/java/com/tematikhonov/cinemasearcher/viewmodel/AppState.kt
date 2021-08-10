package com.tematikhonov.cinemasearcher.viewmodel

import com.tematikhonov.cinemasearcher.model.Cinema

sealed class AppState {
    data class Success(val dataCinema: List<Cinema>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

sealed class AppStateMain{
    data class Success(val dataCinemaNowPlaying: List<Cinema> , val dataCinemaUpcoming: List<Cinema>) : AppStateMain()
    data class Error(val error: Throwable) : AppStateMain()
    object Loading : AppStateMain()
}
