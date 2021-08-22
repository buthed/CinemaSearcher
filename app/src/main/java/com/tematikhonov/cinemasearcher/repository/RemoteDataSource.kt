package com.tematikhonov.cinemasearcher.repository

import com.google.gson.GsonBuilder
import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.model.TMDB_API_CINEMA_URL
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private val cinemaAPI = Retrofit.Builder()
            .baseUrl(TMDB_API_CINEMA_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build().create(CinemaAPI::class.java)

    fun getCinemaDetails(id: Int, callback: Callback<CinemaDTO>) {
        cinemaAPI.getCinema(id).enqueue(callback)
    }
}