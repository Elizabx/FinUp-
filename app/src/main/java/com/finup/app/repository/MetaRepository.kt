package com.finup.app.repository

import com.finup.app.database.entity.MetaEntity
import kotlinx.coroutines.flow.Flow

interface MetaRepository {
    fun getMetasByUser(userId: Int): Flow<List<MetaEntity>>
    suspend fun insert(meta: MetaEntity)
    suspend fun update(meta: MetaEntity)
    suspend fun delete(meta: MetaEntity)
}