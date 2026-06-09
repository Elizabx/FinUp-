package com.finup.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.finup.app.di.AppViewModelProvider
import com.finup.app.session.SessionManager
import com.finup.app.viewmodel.MetaViewModel
import androidx.compose.foundation.clickable

@Composable
fun MetaScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val userId = sessionManager.getUserId() ?: -1

    val viewModel: MetaViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val metas by viewModel.metas.collectAsState()

    LaunchedEffect(userId) {
        if (userId != -1) {
            viewModel.setUsuario(userId)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_meta") },
                containerColor = Color(0xFF7C4DFF)
            ) {
                Text("+", color = Color.White, fontSize = 24.sp)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0F111A))
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            Text("Minhas Metas", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("Planeje seus objetivos financeiros", color = Color(0xFF8E94A6), fontSize = 14.sp)

            Spacer(modifier = Modifier.height(20.dp))

            if (metas.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                    Text("Nenhuma meta definida. Toque no '+' para criar!", color = Color(0xFF8E94A6))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(metas) { meta ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("edit_meta/${meta.id}")
                                },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF161926))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(meta.objetivo, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Text("Prazo: ${meta.prazo}", color = Color(0xFF8E94A6), fontSize = 12.sp)

                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Guardado: R$ %.2f".format(meta.valorAtual), color = Color(0xFF00E676), fontSize = 14.sp)
                                    Text("Alvo: R$ %.2f".format(meta.valorAlvo), color = Color(0xFF8E94A6), fontSize = 14.sp)
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                val progresso = if (meta.valorAlvo > 0) (meta.valorAtual / meta.valorAlvo).toFloat() else 0f
                                LinearProgressIndicator(
                                    progress = { progresso.coerceIn(0f, 1f) },
                                    modifier = Modifier.fillMaxWidth().height(8.dp),
                                    color = Color(0xFF7C4DFF),
                                    trackColor = Color(0xFF0F111A),
                                    strokeCap = StrokeCap.Round
                                )

                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "${(progresso.coerceIn(0f, 1f) * 100).toInt()}% concluído",
                                    color = Color(0xFF8E94A6),
                                    fontSize = 12.sp,
                                    modifier = Modifier.align(Alignment.End)
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
}