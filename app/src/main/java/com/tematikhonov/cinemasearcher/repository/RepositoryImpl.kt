package com.tematikhonov.cinemasearcher.repository

import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.getCinemasListNowPlaying


class RepositoryImpl : Repository {

    override fun getCinemaFromLocalSource(id: Int): Cinema = Cinema()
    override fun getCinemaListFromLocalSource(): List<Cinema> = getCinemasListNowPlaying()
//    override fun getCinemaListFromLocalSourceNowPlaying(): List<Cinema> = getCinemasListNowPlaying()
//    override fun getCinemaListFromLocalSourceUpcoming(): List<Cinema> = getCinemasListUpcoming()

    override fun getCinemaFromServer(): Cinema = Cinema()
//    override fun getCinemaFromServer(movie_id: Int): Cinema {
//        val dto = CinemaLoader.loadCinema(movie_id)
//        return Cinema(
//                movie_id = movie_id,
//                title = dto?.title,
//                release_date = dto?.release_date,
//                vote_average = dto?.vote_average,
//                budget = dto?.budget,
//                revenue = dto?.revenue,
//                poster_path = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + dto?.poster_path,
//                backdrop_path = "https://www.themoviedb.org/t/p/original/" + dto?.backdrop_path,
//                overview = dto?.overview
//        )
//    }
    override fun getCinemaListFromServer(): List<Cinema> = getCinemasListNowPlaying()

}