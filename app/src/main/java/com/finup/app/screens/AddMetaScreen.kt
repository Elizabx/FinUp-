package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.finup.app.viewmodel.MetaViewModel
import androidx.navigation.NavController

import com.finup.app.database.entity.MetaEntity
import androidx.compose.ui.platform.LocalContext
import com.finup.app.FinUpApplication
import com.finup.app.viewmodel.ViewModelFactory

@Composable
fun AddMetaScreen(
    navController: NavController
) {

    val app =
        LocalContext.current.applicationContext
                as FinUpApplication

    val metaViewModel: MetaViewModel =
        viewModel(
            factory = ViewModelFactory(
                metaRepository =
                    app.container.metaRepository
            )
        )

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

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = valorMeta,
            onValueChange = { valorMeta = it },
            label = { Text("Valor da Meta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = valorAtual,
            onValueChange = { valorAtual = it },
            label = { Text("Valor Atual") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                val metaMeta = valorMeta.toDoubleOrNull()
                val metaAtual = valorAtual.toDoubleOrNull()

                if (
                    titulo.isBlank() ||
                    metaMeta == null || metaMeta <= 0 ||
                    metaAtual == null || metaAtual < 0
                ) {
                    return@Button
                }

                val meta = MetaEntity(
                    id = (0..100000).random(),
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

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Voltar")
        }
    }
}