package com.finup.app.repository

import com.finup.app.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun listarTodas(): Flow<List<TransactionEntity>>

    suspend fun inserir(transaction: TransactionEntity)

    suspend fun deletar(transaction: TransactionEntity)

    suspend fun atualizar(transaction: TransactionEntity)

    suspend fun buscarPorId(id: Int): TransactionEntity?
}