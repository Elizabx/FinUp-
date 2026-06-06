package com.finup.app.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "metas")
data class MetaEntity(

    @PrimaryKey
    val id: Int,

    val titulo: String,

    val valorMeta: Double,

    val valorAtual: Double
)