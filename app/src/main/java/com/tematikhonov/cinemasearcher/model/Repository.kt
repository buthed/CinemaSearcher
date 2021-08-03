package com.tematikhonov.cinemasearcher.model

interface Repository {
    fun getCinemaFromLocalSource(movie_id: Int): Cinema
    fun getCinemaListFromLocalSource(): List<Cinema>
    fun getCinemaListFromLocalSourceNowPlaying(): List<Cinema>
    fun getCinemaListFromLocalSourceUpcoming(): List<Cinema>

    fun getCinemaFromServer(movie_id: Int): Cinema
    fun getCinemaListFromServer(): List<Cinema>

}