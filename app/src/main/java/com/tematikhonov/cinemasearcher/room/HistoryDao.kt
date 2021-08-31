package com.tematikhonov.cinemasearcher.room

import androidx.room.*

import androidx.room.OnConflictStrategy.IGNORE


@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    fun selectAll(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE title LIKE :titleCinema")
    fun selectByWord(titleCinema: String): List<HistoryEntity>

    @Insert(onConflict = IGNORE)
    fun insert(historyEntity: HistoryEntity)

    @Update
    fun update(historyEntity: HistoryEntity)

    @Delete
    fun delete(historyEntity: HistoryEntity)
}