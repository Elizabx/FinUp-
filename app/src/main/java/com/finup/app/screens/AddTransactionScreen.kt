package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.finup.app.FinUpApplication
import com.finup.app.database.entity.TransactionEntity
import com.finup.app.di.rememberAppViewModel
import com.finup.app.viewmodel.TransactionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(navController: NavController) {

    val app = LocalContext.current.applicationContext as FinUpApplication

    val viewModel: TransactionViewModel = rememberAppViewModel()

    var descricao by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("Receita") }

    val categorias = listOf(
        "Alimentação",
        "Transporte",
        "Lazer",
        "Saúde",
        "Educação",
        "Moradia",
        "Salario",
        "Outros"
    )

    var categoriaSelecionada by remember { mutableStateOf(categorias.first()) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Nova Transação", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = valor,
            onValueChange = { valor = it },
            label = { Text("Valor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Text("Tipo da Transação", style = MaterialTheme.typography.titleMedium)

        Row {
            Button(onClick = { tipo = "Receita" }) { Text("Receita") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { tipo = "Despesa" }) { Text("Despesa") }
        }

        Spacer(Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            OutlinedTextField(
                value = categoriaSelecionada,
                onValueChange = {},
                readOnly = true,
                label = { Text("Categoria") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categorias.forEach { categoria ->
                    DropdownMenuItem(
                        text = { Text(categoria) },
                        onClick = {
                            categoriaSelecionada = categoria
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                val valorDouble = valor.toDoubleOrNull()

                if (
                    descricao.isBlank() ||
                    valorDouble == null ||
                    valorDouble <= 0
                ) return@Button

                val tx = TransactionEntity(
                    id = 0,
                    descricao = descricao,
                    valor = valorDouble,
                    tipo = tipo,
                    categoria = categoriaSelecionada
                )

                viewModel.adicionarTransacao(tx)
                navController.popBackStack()
            }
        ) {
            Text("Salvar")
        }

        Spacer(Modifier.height(24.dp))

        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}