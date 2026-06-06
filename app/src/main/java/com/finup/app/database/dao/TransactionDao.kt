package com.finup.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.finup.app.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(
        transaction: TransactionEntity
    )

    @Delete
    suspend fun deletar(
        transaction: TransactionEntity
    )

    @Query("SELECT * FROM transactions")
    fun listarTodas(): Flow<List<TransactionEntity>>
}