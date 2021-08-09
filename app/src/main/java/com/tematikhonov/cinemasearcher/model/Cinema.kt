package com.tematikhonov.cinemasearcher.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cinema(
        val movie_id: Int = 1,
        val title: String? = "This is England",
        val release_date: String?  = "1990",
        val vote_average: String? = "7.0",
        val budget: Int? = 2380000,
        val revenue: Int? = 8176544,
        val poster_path: String? = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUwvcDAZG0nbVQokqZh1oQt8Cpm.jpg",
        val backdrop_path: String? = "https://www.themoviedb.org/t/p/original/znV3ucZoH7g7MHZwjW7MlXzo5Pl.jpg",
        val overview: String?  = "Cinema"
): Parcelable

fun getCinemasListNowPlaying() = listOf(
        Cinema(11798,"This is England", "2006", "7.5", 2380000, 8176544, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUwvcDAZG0nbVQokqZh1oQt8Cpm.jpg","https://www.themoviedb.org/t/p/original/znV3ucZoH7g7MHZwjW7MlXzo5Pl.jpg", "A story about a troubled boy growing up in England, set in 1983. He comes across a few skinheads on his way home from school, after a fight. They become his new best friends, even like family. Based on experiences of director Shane Meadows."),
        Cinema(680, "Pulp fiction", "1994", "8.6",8000000, 214179088, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg", "https://www.themoviedb.org/t/p/original/suaEOtk1N1sgg2MTM7oZd2cfVp3.jpg", "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time."),
        Cinema(550,"Fight club", "1999", "8.6", 63000000, 100853753, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "https://www.themoviedb.org/t/p/original/rr7E0NoGKxvbkb89eR1GwfoYjpA.jpg", "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion."),
        Cinema(1992,"Planet Terror", "2007", "6.7", 0, 0 , "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/30ANoUgvqQZ9tgCnM6OyJZUveaf.jpg", "https://www.themoviedb.org/t/p/original/bUmaGlXAtAizyo6zArOP2YDVKZB.jpg", "Two doctors find their graveyard shift inundated with townspeople ravaged by sores. Among the wounded is Cherry, a dancer whose leg was ripped from her body. As the invalids quickly become enraged aggressors, Cherry and her ex-boyfriend Wray lead a team of accidental warriors into the night."),
        Cinema(13,"Forrest Gump", "1994", "8.9",55000000, 677387716, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h5J4W4veyxMXDMjeNxZI46TsHOb.jpg", "https://www.themoviedb.org/t/p/original/tlEFuIlaxRPXIYVHXbOSAMCfWqk.jpg", "A man with a low IQ has accomplished great things in his life and been present during significant historic events—in each case, far exceeding what anyone imagined he could do. But despite all he has achieved, his one true love eludes him.")
)

fun getCinemasListUpcoming() = listOf(
        Cinema(550,"Fight club", "1999", "8.6", 63000000, 100853753, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "https://www.themoviedb.org/t/p/original/rr7E0NoGKxvbkb89eR1GwfoYjpA.jpg", "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion."),
        Cinema(1992,"Planet Terror", "2007", "6.7", 0, 0 , "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/30ANoUgvqQZ9tgCnM6OyJZUveaf.jpg", "https://www.themoviedb.org/t/p/original/bUmaGlXAtAizyo6zArOP2YDVKZB.jpg", "Two doctors find their graveyard shift inundated with townspeople ravaged by sores. Among the wounded is Cherry, a dancer whose leg was ripped from her body. As the invalids quickly become enraged aggressors, Cherry and her ex-boyfriend Wray lead a team of accidental warriors into the night."),
        Cinema(11798,"This is England", "2006", "7.5", 2380000, 8176544, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUwvcDAZG0nbVQokqZh1oQt8Cpm.jpg","https://www.themoviedb.org/t/p/original/znV3ucZoH7g7MHZwjW7MlXzo5Pl.jpg", "A story about a troubled boy growing up in England, set in 1983. He comes across a few skinheads on his way home from school, after a fight. They become his new best friends, even like family. Based on experiences of director Shane Meadows."),
        Cinema(680, "Pulp fiction", "1994", "8.6",8000000, 214179088, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg", "https://www.themoviedb.org/t/p/original/suaEOtk1N1sgg2MTM7oZd2cfVp3.jpg", "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time."),
        Cinema(13,"Forrest Gump", "1994", "8.9",55000000, 677387716, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h5J4W4veyxMXDMjeNxZI46TsHOb.jpg", "https://www.themoviedb.org/t/p/original/tlEFuIlaxRPXIYVHXbOSAMCfWqk.jpg", "A man with a low IQ has accomplished great things in his life and been present during significant historic events—in each case, far exceeding what anyone imagined he could do. But despite all he has achieved, his one true love eludes him.")
)

