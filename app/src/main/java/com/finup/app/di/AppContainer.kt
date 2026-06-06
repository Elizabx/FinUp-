package com.finup.app.di

import android.content.Context
import com.finup.app.database.DatabaseProvider
import com.finup.app.repository.MetaRepository
import com.finup.app.repository.TransactionRepository

class AppContainer(
    context: Context
) {

    private val database =
        DatabaseProvider.getDatabase(context)

    val transactionRepository =
        TransactionRepository(
            database.transactionDao()
        )

    val metaRepository =
        MetaRepository(
            database.metaDao()
        )
}