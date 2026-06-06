package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.finup.app.FinUpApplication
import com.finup.app.viewmodel.EditTransactionViewModel
import com.finup.app.viewmodel.TransactionViewModelFactory

@Composable
fun EditTransactionScreen(
    navController: NavController,
    transactionId: Int,
    app: FinUpApplication = LocalContext.current.applicationContext as FinUpApplication
) {

    val viewModel: EditTransactionViewModel = viewModel(
        factory = TransactionViewModelFactory(
            app.container.transactionRepository
        )
    )

    LaunchedEffect(transactionId) {
        viewModel.carregar(transactionId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Editar Transação")

        Spacer(Modifier.height(12.dp))

        TextField(
            value = viewModel.valor,
            onValueChange = viewModel::onValorChange,
            label = { Text("Valor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = viewModel.descricao,
            onValueChange = viewModel::onDescricaoChange,
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = viewModel.categoria,
            onValueChange = viewModel::onCategoriaChange,
            label = { Text("Categoria") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = viewModel.tipo,
            onValueChange = viewModel::onTipoChange,
            label = { Text("Tipo") },
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
            Text("Salvar")
        }
    }
}