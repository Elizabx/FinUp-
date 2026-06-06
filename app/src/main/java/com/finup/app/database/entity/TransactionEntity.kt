package com.finup.app.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(

    @PrimaryKey
    val id: Int,

    val descricao: String,

    val valor: Double,

    val tipo: String,

    val categoria: String
)