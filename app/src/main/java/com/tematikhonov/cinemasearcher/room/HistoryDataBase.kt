package com.tematikhonov.cinemasearcher.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [HistoryEntity::class], version = 3, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {
    abstract fun historyDao() : HistoryDao
}

val MIGRATION_1_2: Migration = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE HistoryEntity ADD COLUMN note TEXT  DEFAULT 'null'")
    }
}

val MIGRATION_2_3: Migration = object : Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE HistoryEntity ADD COLUMN isLike INTEGER NOT NULL DEFAULT 0")
    }
}
