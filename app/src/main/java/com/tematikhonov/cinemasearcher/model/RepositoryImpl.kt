package com.tematikhonov.cinemasearcher.model

class RepositoryImpl : Repository {
    override fun getCinemaFromLocalSource(movie_id: Int): Cinema = Cinema()
    override fun getCinemaListFromLocalSource(): List<Cinema> = getCinemasListNowPlaying()
    override fun getCinemaListFromLocalSourceNowPlaying(): List<Cinema> = getCinemasListNowPlaying()
    override fun getCinemaListFromLocalSourceUpcoming(): List<Cinema> = getCinemasListUpcoming()

    override fun getCinemaFromServer(movie_id: Int): Cinema = Cinema()
    override fun getCinemaListFromServer(): List<Cinema> = getCinemasListNowPlaying()
}