package com.tematikhonov.cinemasearcher.model

import com.tematikhonov.cinemasearcher.model.entites.Cinema

sealed class AppState {
    data class Success(val cinemaData: List<Cinema>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
