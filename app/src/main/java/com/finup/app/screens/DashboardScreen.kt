package com.finup.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.finup.app.di.AppViewModelProvider
import com.finup.app.session.SessionManager
import com.finup.app.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val userId = sessionManager.getUserId() ?: -1

    val viewModel: DashboardViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        if (userId != -1) viewModel.load(userId)
    }

    val saldoTotal = state.entradasMes - state.saidasMes

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF161926))
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("Início", color = Color(0xFF7C4DFF), fontWeight = FontWeight.Bold, modifier = Modifier.clickable { })
                Text("Extrato", color = Color(0xFF8E94A6), modifier = Modifier.clickable { navController.navigate("extrato") })
                Text("Metas", color = Color(0xFF8E94A6), modifier = Modifier.clickable { navController.navigate("meta") })
                Text("Relatório", color = Color(0xFF8E94A6), modifier = Modifier.clickable { navController.navigate("relatorio") })
                Text("Perfil", color = Color(0xFF8E94A6), modifier = Modifier.clickable { navController.navigate("perfil") })
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0F111A))
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Olá, Bem-vindo!", color = Color(0xFF8E94A6), fontSize = 14.sp)
                    Text("FinUp! Control", color = Color(0xFFFFFFFF), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { navController.navigate("add_transaction") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C4DFF)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("+ Novo", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF161926))
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("SALDO ATUAL", color = Color(0xFF8E94A6), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "R$ %.2f".format(saldoTotal),
                        color = if (saldoTotal >= 0) Color(0xFF00E676) else Color(0xFFFF5252),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF161926))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Entradas", color = Color(0xFF8E94A6), fontSize = 12.sp)
                        Text("R$ %.2f".format(state.entradasMes), color = Color(0xFF00E676), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF161926))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Saídas", color = Color(0xFF8E94A6), fontSize = 12.sp)
                        Text("R$ %.2f".format(state.saidasMes), color = Color(0xFFFF5252), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text("Transações Recentes", color = Color(0xFFFFFFFF), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            if (state.ultimasTransacoes.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                    Text("Nenhuma movimentação ainda.", color = Color(0xFF8E94A6))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.ultimasTransacoes.take(5)) { transacao ->
                        Card(
                            modifier = Modifier.fillMaxWidth().clickable {
                                navController.navigate("edit_transaction/${transacao.id}")
                            },
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF161926))
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(transacao.descricao, color = Color(0xFFFFFFFF), fontWeight = FontWeight.Medium)
                                    Text(transacao.categoria, color = Color(0xFF8E94A6), fontSize = 12.sp)
                                }
                                Text(
                                    text = "${if (transacao.tipo == "ENTRADA") "+" else "-"} R$ %.2f".format(transacao.valor),
                                    color = if (transacao.tipo == "ENTRADA") Color(0xFF00E676) else Color(0xFFFF5252),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}