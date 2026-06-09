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
fun ExtratoScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val userId = sessionManager.getUserId() ?: -1

    val viewModel: DashboardViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        if (userId != -1) viewModel.load(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F111A))
            .padding(20.dp)
    ) {
        Text("Histórico Completo", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Todas as suas movimentações", color = Color(0xFF8E94A6), fontSize = 14.sp)

        Spacer(modifier = Modifier.height(20.dp))

        if (state.ultimasTransacoes.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().weight(1f), contentAlignment = Alignment.Center) {
                Text("Nenhuma transação encontrada.", color = Color(0xFF8E94A6))
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.ultimasTransacoes) { transacao ->
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
                                Text(transacao.descricao, color = Color.White, fontWeight = FontWeight.Medium)
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(transacao.categoria, color = Color(0xFF8E94A6), fontSize = 12.sp)
                                    Text(transacao.data, color = Color(0xFF8E94A6), fontSize = 12.sp)
                                }
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

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF161926)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Voltar ao Dashboard", color = Color.White)
        }
    }
}