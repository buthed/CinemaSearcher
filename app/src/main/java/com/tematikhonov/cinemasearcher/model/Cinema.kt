package com.tematikhonov.cinemasearcher.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cinema(
        val id: Int = 1,
        val movie_id: Long = 1,
        val title: String? = "This is England",
        val release_date: String?  = "1990",
        val vote_average: String? = "7.0",
        val budget: Int? = 2380000,
        val revenue: Int? = 8176544,
        val poster_path: String? = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUwvcDAZG0nbVQokqZh1oQt8Cpm.jpg",
        val backdrop_path: String? = "https://www.themoviedb.org/t/p/original/znV3ucZoH7g7MHZwjW7MlXzo5Pl.jpg",
        val overview: String?  = "Cinema",
        var note: String? = "Default note",
        var isLike: Byte? = 0
): Parcelable


