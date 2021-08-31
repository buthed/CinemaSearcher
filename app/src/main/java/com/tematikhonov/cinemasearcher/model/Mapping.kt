package com.tematikhonov.cinemasearcher.model

import com.tematikhonov.cinemasearcher.room.HistoryEntity

fun convertCinemaDtoToModel(cinemaDTO: CinemaDTO): List<Cinema> { //FIXME
    return listOf(
            Cinema(
                    cinemaDTO.id,
                    cinemaDTO.id.toLong(),
                    cinemaDTO.title,
                    cinemaDTO.release_date,
                    cinemaDTO.vote_average,
                    cinemaDTO.budget,
                    cinemaDTO.revenue,
                    cinemaDTO.poster_path,
                    cinemaDTO.backdrop_path,
                    cinemaDTO.overview
            )) //FIXME
}

fun convertToEntityModel(historyEntity: List<HistoryEntity>): List<Cinema> {
    return historyEntity.map {
        Cinema(it.id.toInt(),
                it.id,
                it.title,
                it.release_date,
                it.vote_average,
                it.budget,
                it.revenue,
                it.poster_path,
                it.backdrop_path,
                it.overview
        )
    }
}

fun convertModelToEntity(cinema: Cinema): HistoryEntity {
    return HistoryEntity(
            0,
            cinema.id,
            cinema.title,
            cinema.release_date,
            cinema.vote_average,
            cinema.budget,
            cinema.revenue,
            cinema.poster_path,
            cinema.backdrop_path,
            cinema.overview)
}