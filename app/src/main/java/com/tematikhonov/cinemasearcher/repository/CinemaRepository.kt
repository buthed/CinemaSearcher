package com.tematikhonov.cinemasearcher.repository

import com.tematikhonov.cinemasearcher.model.CinemaDTO
import retrofit2.Callback

interface CinemaRepository {
    fun getCinemaFromServer(id: Int,callback: Callback<CinemaDTO>)
}