package com.tematikhonov.cinemasearcher.repository

import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.CinemaDTO

interface HistoryRepository {
    fun getAllHistory(): List<Cinema>
    fun saveEntity(cinema: Cinema)
    fun deleteEntityByName(name:String)
}