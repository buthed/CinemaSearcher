package com.tematikhonov.cinemasearcher.model

class RepositoryImpl : Repository {

    override fun getCinemaFromLocalSource(movie_id: Int): Cinema = Cinema()
    override fun getCinemaListFromLocalSource(): List<Cinema> = getCinemasList()
    override fun getCinemaListFromLocalSourceNowPlaying(): List<Cinema> = getCinemasList()
    override fun getCinemaListFromLocalSourceUpcoming(): List<Cinema> = getCinemasList()

    override fun getCinemaFromServer(movie_id: Int): Cinema = Cinema()
    override fun getCinemaListFromServer(): List<Cinema> = getCinemasList()

}