package com.finup.app.di

import android.content.Context
import com.finup.app.database.FinUpDatabase
import com.finup.app.repository.*

interface AppContainer {
    val transactionRepository: TransactionRepository
    val metaRepository: MetaRepository
    val usuarioRepository: UsuarioRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val transactionRepository: TransactionRepository by lazy {
        TransactionRepositoryImpl(FinUpDatabase.getDatabase(context).transactionDao())
    }

    override val metaRepository: MetaRepository by lazy {
        MetaRepositoryImpl(FinUpDatabase.getDatabase(context).metaDao())
    }

    override val usuarioRepository: UsuarioRepository by lazy {
        UsuarioRepositoryImpl(FinUpDatabase.getDatabase(context).usuarioDao())
    }
}