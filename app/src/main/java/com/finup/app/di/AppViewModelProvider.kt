package com.finup.app.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.finup.app.FinUpApplication
import com.finup.app.viewmodel.*

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinUpApplication)
            DashboardViewModel(
                transactionRepository = app.container.transactionRepository,
                metaRepository = app.container.metaRepository
            )
        }
        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinUpApplication)
            TransactionViewModel(app.container.transactionRepository)
        }
        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinUpApplication)
            MetaViewModel(app.container.metaRepository)
        }
        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinUpApplication)
            UsuarioViewModel(app.container.usuarioRepository)
        }
    }
}