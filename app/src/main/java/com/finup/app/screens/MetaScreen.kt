package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MetaScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Metas Financeiras", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(20.dp))

        Text("Objetivo: R$ 1.000")
        Text("Atual: R$ 0,00")

        Spacer(Modifier.height(20.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}