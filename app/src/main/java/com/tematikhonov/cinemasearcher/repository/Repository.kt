package com.tematikhonov.cinemasearcher.repository

import com.tematikhonov.cinemasearcher.model.Cinema

interface Repository {
    fun getCinemaListFromLocalSource(): List<Cinema>
//    fun getCinemaListFromLocalSourceNowPlaying(): List<Cinema>
//    fun getCinemaListFromLocalSourceUpcoming(): List<Cinema>
    fun getCinemaFromLocalSource(id: Int): Cinema

    fun getCinemaFromServer(): Cinema
    fun getCinemaListFromServer(): List<Cinema>

}