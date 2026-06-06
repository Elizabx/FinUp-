package com.finup.app.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "metas")
data class MetaEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val titulo: String,
    val valorMeta: Double,
    val valorAtual: Double = 0.0
)