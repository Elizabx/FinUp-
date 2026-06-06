package com.finup.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.finup.app.database.dao.TransactionDao
import com.finup.app.database.dao.MetaDao
import com.finup.app.database.entity.TransactionEntity
import com.finup.app.database.entity.MetaEntity

@Database(
    entities = [
        TransactionEntity::class,
        MetaEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class FinUpDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
    abstract fun metaDao(): MetaDao
}