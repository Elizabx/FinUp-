package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

@Composable
fun AddTransactionScreen(
    navController: NavController
) {

    var descricao by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }

    var tipo by remember { mutableStateOf("Receita") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Nova Movimentação",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = valor,
            onValueChange = { valor = it },
            label = { Text("Valor") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoria") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Tipo")

        Row {

            RadioButton(
                selected = tipo == "Receita",
                onClick = {
                    tipo = "Receita"
                }
            )

            Text("Receita")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = tipo == "Despesa",
                onClick = {
                    tipo = "Despesa"
                }
            )

            Text("Despesa")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {}
        ) {
            Text("Salvar")
        }

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Voltar")
        }
    }
}