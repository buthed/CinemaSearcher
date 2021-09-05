package com.tematikhonov.cinemasearcher.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val movie_id: Int?,
        val title: String?,
        val release_date: String?,
        val vote_average: String?,
        val budget: Int?,
        val revenue: Int?,
        val poster_path: String?,
        val backdrop_path: String?,
        val overview: String?,
        val note: String?,
        val isLike: Byte = 0
//        val looking_time: String?
)