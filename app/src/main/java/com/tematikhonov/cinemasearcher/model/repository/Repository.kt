package com.tematikhonov.cinemasearcher.model.repository

import com.tematikhonov.cinemasearcher.model.entites.Cinema

interface Repository {
    fun getCinemaFromServer(movie_id: Int): Cinema
    fun getCinemaFromLocalStorage(): List<Cinema>
}