package com.tematikhonov.cinemasearcher.viewmodel

import com.tematikhonov.cinemasearcher.model.Cinema

sealed class AppState {
    data class Success(val dataCinema: Cinema) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}