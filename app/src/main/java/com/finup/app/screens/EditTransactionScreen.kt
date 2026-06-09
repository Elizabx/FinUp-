package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.finup.app.di.AppViewModelProvider
import com.finup.app.viewmodel.TransactionViewModel

@Composable
fun EditTransactionScreen(navController: NavController, transactionId: Int) {
    val viewModel: TransactionViewModel = viewModel(factory = AppViewModelProvider.Factory)

    LaunchedEffect(transactionId) {
        viewModel.carregar(transactionId)
    }

    Column(Modifier.padding(16.dp)) {
        Text("Editar Transação", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.title,
            onValueChange = { viewModel.onTitleChange(it) },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.amount,
            onValueChange = { viewModel.onAmountChange(it) },
            label = { Text("Valor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.salvar()
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Alterações")
        }
    }
}