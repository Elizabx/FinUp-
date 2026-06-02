package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

@Composable
fun PerfilScreen(
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
            text = "Meu Perfil",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("Nome:")
                Text("nynyjxs")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Email:")
                Text("nynyjxs@gmail.com")

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {}
                ) {
                    Text("Editar Perfil")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {}
                ) {
                    Text("Alterar Senha")
                }

                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text("Voltar")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        navController.navigate("login")
                    }
                ) {
                    Text("Sair")
                }
            }
        }
    }
}