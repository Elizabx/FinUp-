package com.finup.app.repository

import com.finup.app.database.dao.MetaDao
import com.finup.app.database.entity.MetaEntity

class MetaRepositoryImpl(
    private val dao: MetaDao
) : MetaRepository {

    override fun listarTodas() = dao.getAll()

    override suspend fun inserir(meta: MetaEntity) {
        dao.insert(meta)
    }

    override suspend fun deletar(meta: MetaEntity) {
        dao.delete(meta)
    }

    override suspend fun atualizar(meta: MetaEntity) {
        dao.update(meta)
    }
}