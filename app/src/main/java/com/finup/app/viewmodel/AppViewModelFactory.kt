package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finup.app.di.AppContainer

@Suppress("UNCHECKED_CAST")
class AppViewModelFactory(private val container: AppContainer) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(
                    transactionRepository = container.transactionRepository,
                    metaRepository = container.metaRepository
                ) as T
            }
            modelClass.isAssignableFrom(TransactionViewModel::class.java) -> {
                TransactionViewModel(container.transactionRepository) as T
            }
            modelClass.isAssignableFrom(MetaViewModel::class.java) -> {
                MetaViewModel(container.metaRepository) as T
            }
            modelClass.isAssignableFrom(UsuarioViewModel::class.java) -> {
                UsuarioViewModel(container.usuarioRepository) as T
            }
            else -> throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
        }
    }
}