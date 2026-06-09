package com.finup.app.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "metas")
data class MetaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val objetivo: String,
    val valorAlvo: Double,
    val valorAtual: Double,
    val prazo: String
)