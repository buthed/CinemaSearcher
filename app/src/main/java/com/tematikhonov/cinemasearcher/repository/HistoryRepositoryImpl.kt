package com.tematikhonov.cinemasearcher.repository


import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.room.HistoryDao
import com.tematikhonov.cinemasearcher.room.HistoryEntity

class HistoryRepositoryImpl(private val historyDao: HistoryDao): HistoryRepository {
    override fun getAllHistory(): List<Cinema> {
        return convertToEntityModel(historyDao.selectAll())
    }

    override fun saveEntity(cinema: Cinema) {
        historyDao.insert(convertModelToEntity(cinema))
    }
}

fun convertCinemaDtoToModel(cinemaDTO: CinemaDTO): List<CinemaDTO> { //FIXME
    return listOf(
            CinemaDTO(
                    cinemaDTO.id,
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
        Cinema(
                0,
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
            cinema.title,
            cinema.release_date,
            cinema.vote_average,
            cinema.budget,
            cinema.revenue,
            cinema.poster_path,
            cinema.backdrop_path,
            cinema.overview)
}