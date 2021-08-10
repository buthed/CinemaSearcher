package com.tematikhonov.cinemasearcher.model

interface Repository {
    fun getCinemaListFromLocalSource(): List<Cinema>
    fun getCinemaListFromLocalSourceNowPlaying(): List<Cinema>
    fun getCinemaListFromLocalSourceUpcoming(): List<Cinema>
    fun getCinemaFromLocalSource(movie_id: Int): Cinema

    fun getCinemaFromServer(): Cinema
    fun getCinemaListFromServer(): List<Cinema>

}