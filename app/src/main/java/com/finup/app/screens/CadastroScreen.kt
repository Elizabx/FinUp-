package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CadastroScreen(navController: NavController) {

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Cadastro", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(nome, { nome = it }, label = { Text("Nome") })
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(email, { email = it }, label = { Text("Email") })
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(senha, { senha = it }, label = { Text("Senha") })

        Spacer(Modifier.height(24.dp))

        Button(onClick = { navController.navigate("login") }) {
            Text("Cadastrar")
        }

        Spacer(Modifier.height(8.dp))

        OutlinedButton(onClick = { navController.navigate("login") }) {
            Text("Voltar")
        }
    }
}