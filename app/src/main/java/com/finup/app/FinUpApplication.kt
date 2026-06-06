package com.finup.app

import android.app.Application
import androidx.room.Room
import com.finup.app.database.FinUpDatabase
import com.finup.app.di.AppContainer

class FinUpApplication : Application() {

    lateinit var database: FinUpDatabase
        private set

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            FinUpDatabase::class.java,
            "finup_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        container = AppContainer(database)
    }
}