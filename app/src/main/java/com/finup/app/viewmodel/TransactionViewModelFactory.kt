package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finup.app.repository.TransactionRepository

class TransactionViewModelFactory(
    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            return TransactionViewModel(transactionRepository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido")
    }
}