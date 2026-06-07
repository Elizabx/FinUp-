package com.finup.app.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.finup.app.FinUpApplication
import com.finup.app.viewmodel.AppViewModelFactory

@Composable
inline fun <reified T : ViewModel> rememberAppViewModel(): T {

    val context = LocalContext.current
    val app = context.applicationContext as FinUpApplication

    val factory = AppViewModelFactory(app.container)

    return viewModel(factory = factory)
}