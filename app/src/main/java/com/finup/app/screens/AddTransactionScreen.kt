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
import com.finup.app.database.entity.TransactionEntity
import com.finup.app.viewmodel.TransactionViewModel
import com.finup.app.viewmodel.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    navController: NavController
) {

    val app =
        LocalContext.current.applicationContext
                as FinUpApplication

    val viewModel: TransactionViewModel =
        viewModel(
            factory = ViewModelFactory(
                transactionRepository =
                    app.container.transactionRepository
            )
        )

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
        "Outros"
    )

    var categoriaSelecionada by remember {
        mutableStateOf(categorias.first())
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Nova Transação",
            style = MaterialTheme.typography.headlineMedium
        )

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

        Text(
            text = "Tipo da Transação",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {

            Button(
                onClick = { tipo = "Receita" }
            ) {
                Text("Receita")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { tipo = "Despesa" }
            ) {
                Text("Despesa")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            OutlinedTextField(
                value = categoriaSelecionada,
                onValueChange = {},
                readOnly = true,
                label = { Text("Categoria") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {

                categorias.forEach { categoria ->

                    DropdownMenuItem(
                        text = {
                            Text(categoria)
                        },
                        onClick = {
                            categoriaSelecionada = categoria
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                val transaction = TransactionEntity(
                    id = (0..100000).random(),
                    descricao = descricao,
                    valor = valor.toDoubleOrNull() ?: 0.0,
                    tipo = tipo,
                    categoria = categoriaSelecionada
                )

                viewModel.adicionarTransacao(
                    transaction
                )

                navController.popBackStack()
            }
        ) {
            Text("Salvar")
        }
    }
}