package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finup.app.database.entity.TransactionEntity
import com.finup.app.repository.TransactionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val repository: TransactionRepository
) : ViewModel() {

    val transacoes = repository
        .listarTodas()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun adicionarTransacao(
        transaction: TransactionEntity
    ) {
        viewModelScope.launch {
            repository.inserir(transaction)
        }
    }

    fun removerTransacao(
        transaction: TransactionEntity
    ) {
        viewModelScope.launch {
            repository.deletar(transaction)
        }
    }
}