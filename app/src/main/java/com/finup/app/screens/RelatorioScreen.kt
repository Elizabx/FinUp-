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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.finup.app.FinUpApplication
import com.finup.app.viewmodel.*

@Composable
fun RelatorioScreen(
    navController: NavController
) {
    val app = LocalContext.current.applicationContext as FinUpApplication

    val transactionViewModel: TransactionViewModel = viewModel(
        factory = TransactionViewModelFactory(app.container.transactionRepository)
    )

    val transacoes by transactionViewModel.transacoes.collectAsState()

    val despesasPorCategoria = remember(transacoes) {
        transacoes
            .filter { it.tipo == "Despesa" }
            .groupBy { it.categoria }
            .mapValues { it.value.sumOf { t -> t.valor } }
    }

    val totalGastos = remember(transacoes) {
        transacoes
            .filter { it.tipo == "Despesa" }
            .sumOf { it.valor }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Relatórios", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(20.dp))

        Text("Gastos por Categoria", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        if (despesasPorCategoria.isEmpty()) {

            Card(Modifier.fillMaxWidth()) {
                Text(
                    "Nenhuma despesa cadastrada.",
                    modifier = Modifier.padding(16.dp)
                )
            }

        } else {

            despesasPorCategoria.forEach { (categoria, valor) ->

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
                        Text(categoria)
                        Text("R$ %.2f".format(valor))
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {

                Text("Total de Gastos", style = MaterialTheme.typography.titleMedium)

                Spacer(Modifier.height(8.dp))

                Text(
                    "R$ %.2f".format(totalGastos),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}