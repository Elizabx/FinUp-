package com.finup.app.di

import com.finup.app.database.FinUpDatabase
import com.finup.app.repository.MetaRepository
import com.finup.app.repository.MetaRepositoryImpl
import com.finup.app.repository.TransactionRepository
import com.finup.app.repository.TransactionRepositoryImpl

class AppContainer(database: FinUpDatabase) {

    val transactionRepository: TransactionRepository =
        TransactionRepositoryImpl(database.transactionDao())

    val metaRepository: MetaRepository =
        MetaRepositoryImpl(database.metaDao())
}