package com.finup.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.finup.app.database.dao.MetaDao
import com.finup.app.database.dao.TransactionDao
import com.finup.app.database.entity.MetaEntity
import com.finup.app.database.entity.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class,
        MetaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FinUpDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    abstract fun metaDao(): MetaDao
}