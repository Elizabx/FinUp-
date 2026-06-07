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

        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("Nome:")
                Text("nynyjxs")

                Spacer(Modifier.height(8.dp))

                Text("Email:")
                Text("nynyjxs@gmail.com")

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = { /* futuramente editar perfil */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Editar Perfil")
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = { /* futuramente senha */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Alterar Senha")
                }

                Spacer(Modifier.height(8.dp))

                OutlinedButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Voltar")
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {
                        navController.navigate("login") {
                            popUpTo(0) // limpa stack (logout real)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sair")
                }
            }
        }
    }
}