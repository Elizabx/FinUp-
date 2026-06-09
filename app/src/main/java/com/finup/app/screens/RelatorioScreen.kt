package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.finup.app.di.AppViewModelProvider
import com.finup.app.session.SessionManager
import com.finup.app.viewmodel.DashboardViewModel

@Composable
fun RelatorioScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val userId = sessionManager.getUserId() ?: -1

    val viewModel: DashboardViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        if (userId != -1) {
            viewModel.load(userId)
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Relatório Financeiro", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Resumo Geral", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(8.dp))
                    Text("Total de Entradas: R$ %.2f".format(state.entradasMes))
                    Text("Total de Saídas: R$ %.2f".format(state.saidasMes))
                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
                    val balanco = state.entradasMes - state.saidasMes
                    Text(
                        text = "Balanço Líquido: R$ %.2f".format(balanco),
                        color = if (balanco >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("Distribuição por Categoria (Saídas)", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            val despesas = state.ultimasTransacoes.filter { it.tipo == "SAIDA" }

            if (despesas.isEmpty()) {
                Text("Nenhuma despesa registrada para análise.")
            } else {
                val despesasPorCategoria = despesas.groupBy { it.categoria }

                despesasPorCategoria.forEach { (nomeCategoria, listaDeTransacoes) ->
                    val totalCategoria = listaDeTransacoes.sumOf { kotlin.math.abs(it.valor) }
                    val porcentagem = if (state.saidasMes > 0) totalCategoria / state.saidasMes else 0.0

                    Column(Modifier.padding(vertical = 6.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(nomeCategoria)
                            Text("R$ %.2f (%d%%)".format(totalCategoria, (porcentagem * 100).toInt()))
                        }
                        LinearProgressIndicator(
                            progress = { porcentagem.toFloat().coerceIn(0f, 1f) },
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
                Text("Voltar ao Dashboard")
            }
        }
    }
}