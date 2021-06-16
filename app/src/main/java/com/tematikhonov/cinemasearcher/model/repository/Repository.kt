package com.tematikhonov.cinemasearcher.model.repository

import com.tematikhonov.cinemasearcher.model.entites.Cinema

interface Repository {
    fun getCinemaFromServer(): Cinema
    fun getCinemaFromLocalStorage(): Cinema
}