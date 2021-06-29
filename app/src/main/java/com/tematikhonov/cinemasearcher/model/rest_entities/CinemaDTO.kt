package com.tematikhonov.cinemasearcher.model.rest_entities

data class CinemaDTO(
        val title: String?,
        val release_date: String?,
        val vote_average: String?,
        val budget: Int?,
        val revenue: Int?,
        val poster_path: String?,
        val backdrop_path: String?,
        val overview: String?
)