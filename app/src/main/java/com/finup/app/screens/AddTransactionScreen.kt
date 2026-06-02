package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.finup.app.model.Transaction
import com.finup.app.viewmodel.TransactionViewModel

@Composable
fun AddTransactionScreen(
    navController: NavController,
    viewModel: TransactionViewModel = viewModel()
) {

    var descricao by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("Receita") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Nova Transação", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = valor,
            onValueChange = { valor = it },
            label = { Text("Valor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            Button(onClick = { tipo = "Receita" }) {
                Text("Receita")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { tipo = "Despesa" }) {
                Text("Despesa")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                val tx = Transaction(
                    id = (0..100000).random(),
                    descricao = descricao,
                    valor = valor.toDoubleOrNull() ?: 0.0,
                    tipo = tipo
                )

                viewModel.adicionarTransacao(tx)

                navController.popBackStack()
            }
        ) {
            Text("Salvar")
        }
    }
}