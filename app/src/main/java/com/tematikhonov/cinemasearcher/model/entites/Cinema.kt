package com.tematikhonov.cinemasearcher.model.entites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cinema(
//        val id: Int = 1,
//        val name: String = "This is England",
        val title: String = "This is England",
        val release_date: String  = "1990",
        val vote_average: String = "7.0",
//        val overview: String  = "Cinema",
        val poster_path: String = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUwvcDAZG0nbVQokqZh1oQt8Cpm.jpg"
) : Parcelable

fun getCinemasList(): List<Cinema> {
    return listOf(
            Cinema("This is England", "2006", "7.5", "uri1"),
            Cinema("Pulp fiction", "1994", "8.6", "uri2"),
            Cinema("Fight club", "1999", "8.6", "uri3"),
            Cinema("Planet Terror", "2007", "6.7", "uri4"),
            Cinema("Forrest Gump", "1994", "8.9", "uri5")
    )
}