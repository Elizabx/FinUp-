package com.finup.app.repository

import com.finup.app.database.dao.TransactionDao
import com.finup.app.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

class TransactionRepository(
    private val dao: TransactionDao
) {

    fun listarTodas(): Flow<List<TransactionEntity>> {
        return dao.listarTodas()
    }

    suspend fun inserir(
        transaction: TransactionEntity
    ) {
        dao.inserir(transaction)
    }

    suspend fun deletar(
        transaction: TransactionEntity
    ) {
        dao.deletar(transaction)
    }
}