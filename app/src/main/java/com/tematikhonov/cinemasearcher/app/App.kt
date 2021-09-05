package com.tematikhonov.cinemasearcher.app

import android.app.Application
import androidx.room.Room
import com.tematikhonov.cinemasearcher.room.HistoryDao
import com.tematikhonov.cinemasearcher.room.HistoryDataBase
import com.tematikhonov.cinemasearcher.room.MIGRATION_1_2
import com.tematikhonov.cinemasearcher.room.MIGRATION_2_3

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private var db: HistoryDataBase? = null

        private const val DB_NAME = "HistoryDataBase.db"

        fun getHistoryDao(): HistoryDao {
            if (db == null) {
                synchronized(HistoryDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) {
                            throw IllegalStateException("Application is null while creating HistoryDataBase")
                        }
                        db = Room.databaseBuilder(
                                appInstance!!.applicationContext,
                                HistoryDataBase::class.java,
                                DB_NAME)
                                .allowMainThreadQueries()
                                .addMigrations(MIGRATION_1_2)
                                .addMigrations(MIGRATION_2_3)
                                .build()
                    }
                }
            }
            return db!!.historyDao()
        }
    }
}