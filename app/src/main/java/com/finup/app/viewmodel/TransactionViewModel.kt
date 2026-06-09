package com.finup.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finup.app.database.entity.TransactionEntity
import com.finup.app.repository.TransactionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TransactionViewModel(
    private val repository: TransactionRepository
) : ViewModel() {

    private val userIdState = MutableStateFlow<Int?>(null)

    fun setUsuario(id: Int) {
        userIdState.value = id
    }

    val transacoes: StateFlow<List<TransactionEntity>> = userIdState
        .filterNotNull()
        .flatMapLatest { repository.getTransactionsByUser(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun adicionarTransacao(t: TransactionEntity) {
        viewModelScope.launch {
            repository.insertTransaction(t)
        }
    }

    var amount by mutableStateOf("")
        private set

    var title by mutableStateOf("")
        private set

    private var currentTransactionId: Int = -1
    private var currentUserId: Int = -1
    private var currentType: String = "SAIDA"
    private var currentCategory: String = "Outros"
    private var currentDate: String = ""

    fun onAmountChange(v: String) { amount = v }
    fun onTitleChange(v: String) { title = v }

    fun carregar(transactionId: Int) {
        currentTransactionId = transactionId
        viewModelScope.launch {
            val t = repository.getTransactionById(transactionId) ?: return@launch
            title = t.descricao
            amount = t.valor.toString()
            currentUserId = t.userId
            currentType = t.tipo
            currentCategory = t.categoria
            currentDate = t.data
        }
    }

    fun salvar() {
        val value = amount.toDoubleOrNull() ?: return
        viewModelScope.launch {
            repository.updateTransaction(
                TransactionEntity(
                    id = currentTransactionId,
                    userId = currentUserId,
                    descricao = title,
                    valor = value,
                    tipo = currentType,
                    categoria = currentCategory,
                    data = currentDate
                )
            )
        }
    }
}