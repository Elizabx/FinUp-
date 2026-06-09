package com.finup.app.database.dao

import androidx.room.*
import com.finup.app.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transacoes ORDER BY id DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT SUM(valor) FROM transacoes")
    fun getSaldoTotal(): Flow<Double?>

    @Query("SELECT * FROM transacoes WHERE userId = :userId ORDER BY id DESC")
    fun getTransactionsByUser(userId: Int): Flow<List<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transacoes WHERE id = :id LIMIT 1")
    suspend fun getTransactionById(id: Int): TransactionEntity?
}