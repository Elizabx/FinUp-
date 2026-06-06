package com.finup.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finup.app.database.entity.TransactionEntity
import com.finup.app.repository.TransactionRepository
import kotlinx.coroutines.launch

class EditTransactionViewModel(
    private val repository: TransactionRepository
) : ViewModel() {

    var valor by mutableStateOf("")
        private set

    var descricao by mutableStateOf("")
        private set

    var categoria by mutableStateOf("")
        private set

    var tipo by mutableStateOf("")
        private set

    private var transactionId: Int? = null

    fun carregar(id: Int) {
        transactionId = id

        viewModelScope.launch {
            val transaction = repository.buscarPorId(id) ?: return@launch

            valor = transaction.valor.toString()
            descricao = transaction.descricao
            categoria = transaction.categoria
            tipo = transaction.tipo
        }
    }

    fun onValorChange(v: String) { valor = v }
    fun onDescricaoChange(v: String) { descricao = v }
    fun onCategoriaChange(v: String) { categoria = v }
    fun onTipoChange(v: String) { tipo = v }

    fun salvar() {
        val id = transactionId ?: return

        val valorDouble = valor.replace(",", ".").toDoubleOrNull()

        if (valorDouble == null) {
            return // ou mostrar erro na UI depois
        }

        viewModelScope.launch {

            val updated = TransactionEntity(
                id = id,
                valor = valorDouble,
                descricao = descricao,
                categoria = categoria,
                tipo = tipo
            )

            repository.atualizar(updated)
        }
    }
}