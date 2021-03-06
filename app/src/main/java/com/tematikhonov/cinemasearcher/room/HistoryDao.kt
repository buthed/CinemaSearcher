package com.tematikhonov.cinemasearcher.room

import androidx.room.*

import androidx.room.OnConflictStrategy.IGNORE


@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    fun selectAll(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE title LIKE '%' || :titleCinema || '%'")
    fun searchByAllHistory(titleCinema: String): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE isLike=:like")
    fun selectAllFavorites(like: Byte): List<HistoryEntity>


    @Query("DELETE FROM HistoryEntity WHERE title=:cityName")
    fun deleteByWordTestDelete(cityName: String)

    @Query("DELETE FROM HistoryEntity")
    fun deleteAllHistory()



    @Insert(onConflict = IGNORE)
    fun insert(historyEntity: HistoryEntity)

    @Update
    fun update(historyEntity: HistoryEntity)

    @Delete
    fun delete(historyEntity: HistoryEntity)
}