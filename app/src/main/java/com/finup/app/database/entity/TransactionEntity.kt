package com.finup.app.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transacoes")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val descricao: String,
    val valor: Double,
    val tipo: String,
    val categoria: String,
    val data: String
)