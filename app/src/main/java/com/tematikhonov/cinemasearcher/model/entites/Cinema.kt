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
        val budget: Int = 2380000,
        val revenue: Int = 8176544,
        val poster_path: String = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUwvcDAZG0nbVQokqZh1oQt8Cpm.jpg",
        val overview: String  = "Cinema"

) : Parcelable

fun getCinemasList(): List<Cinema> {
    return listOf(
            Cinema("This is England", "2006", "7.5", 2380000, 8176544, "uri1", "A story about a troubled boy growing up in England, set in 1983. He comes across a few skinheads on his way home from school, after a fight. They become his new best friends, even like family. Based on experiences of director Shane Meadows."),
            Cinema("Pulp fiction", "1994", "8.6",8000000, 214179088, "uri2", "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time."),
            Cinema("Fight club", "1999", "8.6", 63000000, 100853753, "uri3", "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion."),
            Cinema("Planet Terror", "2007", "6.7", 0, 0 , "uri4", "Two doctors find their graveyard shift inundated with townspeople ravaged by sores. Among the wounded is Cherry, a dancer whose leg was ripped from her body. As the invalids quickly become enraged aggressors, Cherry and her ex-boyfriend Wray lead a team of accidental warriors into the night."),
            Cinema("Forrest Gump", "1994", "8.9",55000000, 677387716, "uri5", "A man with a low IQ has accomplished great things in his life and been present during significant historic events—in each case, far exceeding what anyone imagined he could do. But despite all he has achieved, his one true love eludes him.")
    )
}