package com.finup.app.database.dao

import androidx.room.*
import com.finup.app.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions ORDER BY id DESC")
    fun getAll(): Flow<List<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: TransactionEntity)

    @Delete
    suspend fun delete(transaction: TransactionEntity)

    @Update
    suspend fun update(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): TransactionEntity?
}