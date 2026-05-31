package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

@Composable
fun RelatorioScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Relatorios",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Gastos por Categoria")

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("🥗 Alimentação: R$ 500")
                Text("⛽ Transporte: R$ 300")
                Text("🎮 Lazer: R$ 400")
                Text("📚 Estudos: R$ 300")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Total Gastos:")
        Text("R$ 1.500")

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Voltar")
        }
    }
}