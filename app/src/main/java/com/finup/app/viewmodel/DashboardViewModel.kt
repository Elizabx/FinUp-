package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finup.app.database.entity.TransactionEntity
import com.finup.app.repository.MetaRepository
import com.finup.app.repository.TransactionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class DashboardUiState(
    val entradasMes: Double = 0.0,
    val saidasMes: Double = 0.0,
    val ultimasTransacoes: List<TransactionEntity> = emptyList()
)

class DashboardViewModel(
    private val transactionRepository: TransactionRepository,
    private val metaRepository: MetaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun load(userId: Int) {
        viewModelScope.launch {
            transactionRepository.getTransactionsByUser(userId).collect { lista ->
                val entradas = lista.filter { it.tipo == "ENTRADA" }.sumOf { it.valor }
                val saidas = lista.filter { it.tipo == "SAIDA" }.sumOf { kotlin.math.abs(it.valor) }

                _uiState.value = DashboardUiState(
                    entradasMes = entradas,
                    saidasMes = saidas,
                    ultimasTransacoes = lista
                )
            }
        }
    }
}