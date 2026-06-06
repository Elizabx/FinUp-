package com.finup.app.database.dao

import androidx.room.*
import com.finup.app.database.entity.MetaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MetaDao {

    @Query("SELECT * FROM metas")
    fun getAll(): Flow<List<MetaEntity>>

    @Insert
    suspend fun insert(meta: MetaEntity)

    @Delete
    suspend fun delete(meta: MetaEntity)

    @Update
    suspend fun update(meta: MetaEntity)
}