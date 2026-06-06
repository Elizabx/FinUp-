package com.finup.app.repository

import com.finup.app.database.entity.MetaEntity
import kotlinx.coroutines.flow.Flow

interface MetaRepository {

    fun listarTodas(): Flow<List<MetaEntity>>

    suspend fun inserir(meta: MetaEntity)

    suspend fun deletar(meta: MetaEntity)

    suspend fun atualizar(meta: MetaEntity)
}