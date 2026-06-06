package com.finup.app.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: FinUpDatabase? = null

    fun getDatabase(
        context: Context
    ): FinUpDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                FinUpDatabase::class.java,
                "finup_database"
            ).build()

            INSTANCE = instance

            instance
        }
    }
}