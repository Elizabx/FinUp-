package com.finup.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.finup.app.database.dao.MetaDao
import com.finup.app.database.dao.TransactionDao
import com.finup.app.database.dao.UsuarioDao
import com.finup.app.database.entity.MetaEntity
import com.finup.app.database.entity.TransactionEntity
import com.finup.app.database.entity.UsuarioEntity

@Database(
    entities = [TransactionEntity::class, MetaEntity::class, UsuarioEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FinUpDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun metaDao(): MetaDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var Instance: FinUpDatabase? = null

        fun getDatabase(context: Context): FinUpDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    FinUpDatabase::class.java,
                    "finup_database"
                ).fallbackToDestructiveMigration().build().also { Instance = it }
            }
        }
    }
}