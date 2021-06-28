package com.tematikhonov.cinemasearcher.model.repository

import com.tematikhonov.cinemasearcher.model.entites.Cinema
import com.tematikhonov.cinemasearcher.model.entites.getCinemasList
import com.tematikhonov.cinemasearcher.model.rest_entities.CinemaLoader

class RepositoryImpl : Repository {
    override fun getCinemaFromServer(movie_id: Int) : Cinema {
        val dto = CinemaLoader.loadCinema(movie_id)
        return Cinema(
                movie_id = movie_id,
                title = dto?.title,
                release_date = dto?.release_date,
                vote_average = dto?.vote_average,
                budget = dto?.budget,
                revenue = dto?.revenue,
                poster_path = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + dto?.poster_path,
                backdrop_path = "https://www.themoviedb.org/t/p/original/" + dto?.backdrop_path,
                overview = dto?.overview
        )
    }

    override fun getCinemaFromLocalStorage() = getCinemasList()
}