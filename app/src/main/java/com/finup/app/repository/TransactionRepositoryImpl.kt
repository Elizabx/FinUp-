package com.finup.app.repository

import com.finup.app.database.dao.TransactionDao
import com.finup.app.database.entity.TransactionEntity

class TransactionRepositoryImpl(
    private val dao: TransactionDao
) : TransactionRepository {

    override fun listarTodas() = dao.getAll()

    override suspend fun inserir(transaction: TransactionEntity) {
        dao.insert(transaction)
    }

    override suspend fun deletar(transaction: TransactionEntity) {
        dao.delete(transaction)
    }

    override suspend fun atualizar(transaction: TransactionEntity) {
        dao.update(transaction)
    }

    override suspend fun buscarPorId(id: Int): TransactionEntity? {
        return dao.getById(id)
    }
}