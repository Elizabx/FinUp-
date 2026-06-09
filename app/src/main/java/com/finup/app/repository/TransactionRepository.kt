package com.finup.app.repository

import com.finup.app.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getAllTransactionsStream(): Flow<List<TransactionEntity>>
    fun getSaldoStream(): Flow<Double?>
    fun getTransactionsByUser(userId: Int): Flow<List<TransactionEntity>>
    suspend fun insertTransaction(transaction: TransactionEntity)
    suspend fun deleteTransaction(transaction: TransactionEntity)
    suspend fun updateTransaction(transaction: TransactionEntity)
    suspend fun getTransactionById(id: Int): TransactionEntity?
}