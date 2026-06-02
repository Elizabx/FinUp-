package com.finup.app.model

data class Transaction(
    val id: Int,
    val descricao: String,
    val valor: Double,
    val tipo: String // "Receita" ou "Despesa"
)