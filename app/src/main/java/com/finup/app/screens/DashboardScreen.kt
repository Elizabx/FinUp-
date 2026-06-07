package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.finup.app.FinUpApplication
import com.finup.app.navigation.Routes
import com.finup.app.viewmodel.*
import com.finup.app.di.rememberAppViewModel

@Composable
fun DashboardScreen(
    navController: NavController
) {
    val transactionViewModel: TransactionViewModel = rememberAppViewModel()
    val metaViewModel: MetaViewModel = rememberAppViewModel()

    val transacoes by transactionViewModel.transacoes.collectAsState()
    val metas by metaViewModel.metas.collectAsState()

    val receitas = remember(transacoes) {
        transacoes.filter { it.tipo == "Receita" }.sumOf { it.valor }
    }

    val despesas = remember(transacoes) {
        transacoes.filter { it.tipo == "Despesa" }.sumOf { it.valor }
    }

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

        Text("FinUp!", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(24.dp))

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Saldo Atual")
                Text("R$ %.2f".format(saldo))

                Spacer(Modifier.height(12.dp))

                Text("Receitas: R$ %.2f".format(receitas))
                Text("Despesas: R$ %.2f".format(despesas))
            }
        }

        Spacer(Modifier.height(24.dp))

        Text("Metas", style = MaterialTheme.typography.titleLarge)

        metas.forEach { meta ->

            val progresso = if (meta.valorMeta > 0) {
                (meta.valorAtual / meta.valorMeta) * 100
            } else 0.0

            val progressValue = (progresso / 100).toFloat().coerceIn(0f, 1f)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(Modifier.padding(16.dp)) {

                    Text(meta.titulo, style = MaterialTheme.typography.titleLarge)

                    Text("R$ %.2f / R$ %.2f".format(meta.valorAtual, meta.valorMeta))
                    Text("${progresso.toInt()}% concluído")

                    Spacer(Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = progressValue,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

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

                        val valor = valorInput.toDoubleOrNull()

                        if (valor != null && valor > 0) {
                            metaSelecionadaId?.let {
                                metaViewModel.adicionarValor(it, valor)
                            }
                        }

                        showDialog = false
                        valorInput = ""
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

        Spacer(Modifier.height(24.dp))

        Text("Últimas Movimentações", style = MaterialTheme.typography.titleLarge)

        transacoes.forEach { transacao ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {
                        Text(transacao.descricao, style = MaterialTheme.typography.titleMedium)
                        Text("R$ %.2f".format(transacao.valor))
                        Text(transacao.categoria)
                    }

                    Row {
                        OutlinedButton(onClick = {
                            navController.navigate(
                                Routes.EditTransaction.createRoute(transacao.id)
                            )
                        }) {
                            Text("Editar")
                        }

                        Spacer(Modifier.width(8.dp))

                        OutlinedButton(onClick = {
                            transactionViewModel.removerTransacao(transacao)
                        }) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(onClick = { navController.navigate(Routes.AddTransaction.route) }) {
            Text("Nova Movimentação")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = { navController.navigate(Routes.AddMeta.route) }) {
            Text("Criar Meta")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = { navController.navigate(Routes.Meta.route) }) {
            Text("Metas Financeiras")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = { navController.navigate(Routes.Relatorio.route) }) {
            Text("Relatórios")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = { navController.navigate(Routes.Perfil.route) }) {
            Text("Perfil")
        }
    }
}