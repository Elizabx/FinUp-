package com.finup.app.model

data class Transacao(
    val descricao: String,
    val valor: Double,
    val categoria: String,
    val tipo: String
)