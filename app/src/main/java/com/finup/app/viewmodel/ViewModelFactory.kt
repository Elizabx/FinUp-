package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finup.app.repository.TransactionRepository
import com.finup.app.repository.MetaRepository

class ViewModelFactory(
    private val transactionRepository: TransactionRepository? = null,
    private val metaRepository: MetaRepository? = null
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                TransactionViewModel::class.java
            )
        ) {
            return TransactionViewModel(
                transactionRepository!!
            ) as T
        }

        if (
            modelClass.isAssignableFrom(
                MetaViewModel::class.java
            )
        ) {
            return MetaViewModel(
                metaRepository!!
            ) as T
        }

        throw IllegalArgumentException(
            "ViewModel desconhecida"
        )
    }
}