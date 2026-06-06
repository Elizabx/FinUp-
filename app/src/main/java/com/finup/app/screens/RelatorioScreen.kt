package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.finup.app.viewmodel.TransactionViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.finup.app.FinUpApplication
import com.finup.app.viewmodel.ViewModelFactory

@Composable
fun RelatorioScreen(
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

    val gastosPorCategoria =
        transacoes
            .filter { it.tipo == "Despesa" }
            .groupBy { it.categoria }
            .mapValues { grupo ->
                grupo.value.sumOf { it.valor }
            }

    val totalGastos =
        transacoes
            .filter { it.tipo == "Despesa" }
            .sumOf { it.valor }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Relatórios",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Gastos por Categoria",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (gastosPorCategoria.isEmpty()) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Nenhuma despesa cadastrada.",
                    modifier = Modifier.padding(16.dp)
                )
            }

        } else {

            gastosPorCategoria.forEach { (categoria, valor) ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(categoria)

                        Text(
                            text = "R$ %.2f".format(valor)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Total de Gastos",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "R$ %.2f".format(totalGastos),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Voltar")
        }
    }
}