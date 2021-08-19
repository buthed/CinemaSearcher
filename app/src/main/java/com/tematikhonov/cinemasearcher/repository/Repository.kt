package com.tematikhonov.cinemasearcher.repository

import com.tematikhonov.cinemasearcher.model.Cinema

interface Repository {
    fun getCinemaListFromLocalSource(): Cinema

}