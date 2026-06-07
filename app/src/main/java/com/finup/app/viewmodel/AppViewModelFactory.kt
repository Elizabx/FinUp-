package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finup.app.di.AppContainer

class AppViewModelFactory(
    private val container: AppContainer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {

            modelClass.isAssignableFrom(TransactionViewModel::class.java) -> {
                TransactionViewModel(container.transactionRepository) as T
            }

            modelClass.isAssignableFrom(MetaViewModel::class.java) -> {
                MetaViewModel(container.metaRepository) as T
            }

            modelClass.isAssignableFrom(EditTransactionViewModel::class.java) -> {
                EditTransactionViewModel(container.transactionRepository) as T
            }

            else -> throw IllegalArgumentException("ViewModel não encontrado: ${modelClass.name}")
        }
    }
}