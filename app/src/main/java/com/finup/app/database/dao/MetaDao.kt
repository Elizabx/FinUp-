package com.finup.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.finup.app.database.entity.MetaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MetaDao {

    @Insert
    suspend fun inserir(
        meta: MetaEntity
    )

    @Update
    suspend fun atualizar(
        meta: MetaEntity
    )

    @Delete
    suspend fun deletar(
        meta: MetaEntity
    )

    @Query("SELECT * FROM metas")
    fun listarTodas(): Flow<List<MetaEntity>>
}