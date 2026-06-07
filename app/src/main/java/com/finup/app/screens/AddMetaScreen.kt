package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.finup.app.viewmodel.MetaViewModel
import androidx.navigation.NavController
import com.finup.app.di.rememberAppViewModel

import com.finup.app.database.entity.MetaEntity
import androidx.compose.ui.platform.LocalContext
import com.finup.app.FinUpApplication

@Composable
fun AddMetaScreen(navController: NavController) {

    val app = LocalContext.current.applicationContext as FinUpApplication

    val metaViewModel: MetaViewModel = rememberAppViewModel()

    var titulo by remember { mutableStateOf("") }
    var valorMeta by remember { mutableStateOf("") }
    var valorAtual by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Nova Meta", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = valorMeta,
            onValueChange = { valorMeta = it },
            label = { Text("Valor da Meta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = valorAtual,
            onValueChange = { valorAtual = it },
            label = { Text("Valor Atual") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                val metaMeta = valorMeta.toDoubleOrNull()
                val metaAtual = valorAtual.toDoubleOrNull()

                if (
                    titulo.isBlank() ||
                    metaMeta == null || metaMeta <= 0 ||
                    metaAtual == null || metaAtual < 0
                ) return@Button

                val meta = MetaEntity(
                    id = 0,
                    titulo = titulo,
                    valorMeta = metaMeta,
                    valorAtual = metaAtual
                )

                metaViewModel.adicionarMeta(meta)
                navController.popBackStack()
            }
        ) {
            Text("Salvar Meta")
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
    }
}