package com.finup.app.repository

import com.finup.app.database.dao.TransactionDao
import com.finup.app.database.entity.TransactionEntity
import com.finup.app.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(private val transactionDao: TransactionDao) : TransactionRepository {
    override fun getAllTransactionsStream(): Flow<List<TransactionEntity>> = transactionDao.getAllTransactions()
    override fun getSaldoStream(): Flow<Double?> = transactionDao.getSaldoTotal()
    override fun getTransactionsByUser(userId: Int): Flow<List<TransactionEntity>> = transactionDao.getTransactionsByUser(userId)
    override suspend fun insertTransaction(transaction: TransactionEntity) = transactionDao.insertTransaction(transaction)
    override suspend fun deleteTransaction(transaction: TransactionEntity) = transactionDao.deleteTransaction(transaction)
    override suspend fun updateTransaction(transaction: TransactionEntity) = transactionDao.updateTransaction(transaction)
    override suspend fun getTransactionById(id: Int): TransactionEntity? = transactionDao.getTransactionById(id)
}