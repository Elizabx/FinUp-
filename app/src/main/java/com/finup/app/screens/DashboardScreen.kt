package com.finup.app.screens

import com.finup.app.navigation.Routes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator

import androidx.navigation.NavController

import com.finup.app.viewmodel.MetaViewModel
import com.finup.app.viewmodel.TransactionViewModel

import androidx.compose.ui.platform.LocalContext
import com.finup.app.FinUpApplication
import com.finup.app.viewmodel.ViewModelFactory

@Composable
fun DashboardScreen(
    navController: NavController
) {

    val app =
        LocalContext.current.applicationContext
                as FinUpApplication

    val transactionViewModel: TransactionViewModel =
        viewModel(
            factory = ViewModelFactory(
                transactionRepository =
                    app.container.transactionRepository
            )
        )

    val transacoes =
        transactionViewModel.transacoes.collectAsState().value

    val metaViewModel: MetaViewModel =
        viewModel(
            factory = ViewModelFactory(
                metaRepository =
                    app.container.metaRepository
            )
        )

    val metas =
        metaViewModel.metas.collectAsState().value

    val receitas = transacoes
        .filter { it.tipo == "Receita" }
        .sumOf { it.valor }

    val despesas = transacoes
        .filter { it.tipo == "Despesa" }
        .sumOf { it.valor }

    val saldo = receitas - despesas

    var showDialog by remember { mutableStateOf(false) }
    var valorInput by remember { mutableStateOf("") }
    var metaSelecionadaId by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "FinUp!",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text("Saldo Atual")
                Text("R$ $saldo")

                Spacer(modifier = Modifier.height(12.dp))

                Text("Receitas: R$ $receitas")
                Text("Despesas: R$ $despesas")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Metas", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))

        metas.forEach { meta ->

            val progresso = if (meta.valorMeta > 0)
                (meta.valorAtual / meta.valorMeta) * 100
            else 0.0

            val progressValue =
                (progresso / 100).toFloat().coerceIn(0f, 1f)

            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(meta.titulo, style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.height(6.dp))

                    Text("R$ ${meta.valorAtual} / R$ ${meta.valorMeta}")
                    Text("${progresso.toInt()}% concluído")

                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = progressValue,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        OutlinedButton(onClick = {
                            metaSelecionadaId = meta.id
                            showDialog = true
                        }) {
                            Text("+ valor")
                        }

                        OutlinedButton(onClick = {
                            metaViewModel.removerMeta(meta)
                        }) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    valorInput = ""
                    metaSelecionadaId = null
                },
                title = { Text("Adicionar valor") },
                text = {
                    OutlinedTextField(
                        value = valorInput,
                        onValueChange = { valorInput = it },
                        label = { Text("Valor") }
                    )
                },
                confirmButton = {
                    Button(onClick = {

                        val valor = valorInput.toDoubleOrNull() ?: 0.0

                        //metaSelecionadaId?.let {
                            //metaViewModel.adicionarValor(it, valor)
                        //}

                        valorInput = ""
                        showDialog = false
                        metaSelecionadaId = null
                    }) {
                        Text("Adicionar")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = {
                        showDialog = false
                    }) {
                        Text("Cancelar")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Últimas Movimentações", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))

        transacoes.forEach {
            Text("${it.descricao} - R$ ${it.valor}")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            navController.navigate(Routes.AddTransaction.route)
        }) { Text("Nova Movimentação") }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            navController.navigate(Routes.AddMeta.route)
        }) { Text("Criar Meta") }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            navController.navigate(Routes.Meta.route)
        }) { Text("Metas Financeiras") }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            navController.navigate(Routes.Relatorio.route)
        }) { Text("Relatórios") }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            navController.navigate(Routes.Perfil.route)
        }) { Text("Perfil") }
    }
}