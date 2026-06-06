package com.finup.app.repository

import com.finup.app.database.dao.MetaDao
import com.finup.app.database.entity.MetaEntity
import kotlinx.coroutines.flow.Flow

class MetaRepository(
    private val dao: MetaDao
) {

    fun listarTodas(): Flow<List<MetaEntity>> {
        return dao.listarTodas()
    }

    suspend fun inserir(
        meta: MetaEntity
    ) {
        dao.inserir(meta)
    }

    suspend fun atualizar(
        meta: MetaEntity
    ) {
        dao.atualizar(meta)
    }

    suspend fun deletar(
        meta: MetaEntity
    ) {
        dao.deletar(meta)
    }
}