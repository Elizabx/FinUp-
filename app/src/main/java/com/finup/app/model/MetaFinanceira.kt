package com.finup.app.model

data class MetaFinanceira(
    val id: Int = 0,
    val titulo: String,
    val valorMeta: Double,
    val valorAtual: Double
)