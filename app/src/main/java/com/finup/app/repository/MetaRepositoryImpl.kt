package com.finup.app.repository

import com.finup.app.database.dao.MetaDao
import com.finup.app.database.entity.MetaEntity
import kotlinx.coroutines.flow.Flow

class MetaRepositoryImpl(private val metaDao: MetaDao) : MetaRepository {
    override fun getMetasByUser(userId: Int): Flow<List<MetaEntity>> = metaDao.getMetasByUsuario(userId)
    override suspend fun insert(meta: MetaEntity) = metaDao.insertMeta(meta)
    override suspend fun update(meta: MetaEntity) = metaDao.updateMeta(meta)
    override suspend fun delete(meta: MetaEntity) = metaDao.deleteMeta(meta)
}