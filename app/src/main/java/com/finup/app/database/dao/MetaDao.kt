package com.finup.app.database.dao

import androidx.room.*
import com.finup.app.database.entity.MetaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MetaDao {
    @Query("SELECT * FROM metas WHERE userId = :userId")
    fun getMetasByUsuario(userId: Int): Flow<List<MetaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeta(meta: MetaEntity)

    @Update
    suspend fun updateMeta(meta: MetaEntity)

    @Delete
    suspend fun deleteMeta(meta: MetaEntity)
}