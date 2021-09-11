package com.tematikhonov.cinemasearcher.repository


import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.model.convertModelToEntity
import com.tematikhonov.cinemasearcher.model.convertToEntityModel
import com.tematikhonov.cinemasearcher.room.HistoryDao
import com.tematikhonov.cinemasearcher.room.HistoryEntity

class HistoryRepositoryImpl(private val historyDao: HistoryDao): HistoryRepository {
    override fun getAllHistory(): List<Cinema> {
        return convertToEntityModel(historyDao.selectAll())
    }

    override fun saveEntity(cinema: Cinema) {
        historyDao.insert(convertModelToEntity(cinema))
    }

    override fun deleteEntityByName(name: String) {
        historyDao.deleteByWordTestDelete(name)
    }

    override fun deleteAllHitstory() {
        historyDao.deleteAllHistory()
    }

    override fun searchByAllHistory(titleCinema: String) : List<Cinema> {
        return convertToEntityModel(historyDao.searchByAllHistory(titleCinema))
    }
}

