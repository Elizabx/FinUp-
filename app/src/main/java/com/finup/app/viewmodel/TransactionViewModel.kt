package com.finup.app.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.finup.app.model.Transaction

class TransactionViewModel : ViewModel() {

    private val _transacoes = mutableStateListOf<Transaction>()
    val transacoes: List<Transaction> = _transacoes

    fun adicionarTransacao(transaction: Transaction) {
        _transacoes.add(transaction)
    }

    fun removerTransacao(id: Int) {
        _transacoes.removeIf { it.id == id }
    }
}