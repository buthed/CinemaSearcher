package com.tematikhonov.cinemasearcher.model

sealed class AppState {
    data class Success(val cinemaData: Any) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
