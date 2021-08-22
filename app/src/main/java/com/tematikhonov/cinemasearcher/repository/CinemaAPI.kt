package com.tematikhonov.cinemasearcher.repository

import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.model.TMDB_API_CINEMA_URL_END_POINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CinemaAPI {
    @GET("$TMDB_API_CINEMA_URL_END_POINT{id}")
    fun getCinema(
            @Path("id") id: Int,
            @Query("api_key") key: String = "3ecf2512edd67e7138c00bb4d44064d9"
    ): Call<CinemaDTO>
}