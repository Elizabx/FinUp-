package com.finup.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.finup.app.database.entity.MetaEntity
import com.finup.app.di.AppViewModelProvider
import com.finup.app.session.SessionManager
import com.finup.app.viewmodel.MetaViewModel

@Composable
fun EditMetaScreen(navController: NavController, metaId: Int) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val userId = sessionManager.getUserId() ?: -1

    val viewModel: MetaViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val metas by viewModel.metas.collectAsState()

    var valorAAjustarStr by remember { mutableStateOf("") }
    var mensagemErro by remember { mutableStateOf("") }

    LaunchedEffect(userId) {
        if (userId != -1) {
            viewModel.setUsuario(userId)
        }
    }

    val metaOriginal = remember(metas, metaId) {
        metas.find { it.id == metaId }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F111A))
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text("Poupar para a Meta", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Adicione dinheiro ao seu objetivo", color = Color(0xFF8E94A6), fontSize = 14.sp)

        Spacer(modifier = Modifier.height(32.dp))

        if (metaOriginal == null) {
            Box(
                modifier = Modifier.fillMaxWidth().height(120.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF7C4DFF))
            }
        } else {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF161926))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(metaOriginal.objetivo, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Prazo: ${metaOriginal.prazo}", color = Color(0xFF8E94A6), fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Guardado: R$ %.2f".format(metaOriginal.valorAtual), color = Color(0xFF00E676), fontWeight = FontWeight.Bold)
                        Text("Alvo: R$ %.2f".format(metaOriginal.valorAlvo), color = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = valorAAjustarStr,
            onValueChange = { valorAAjustarStr = it },
            label = { Text("Quanto quer adicionar? (R$)", color = Color(0xFF8E94A6)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF161926), unfocusedContainerColor = Color(0xFF161926),
                focusedBorderColor = Color(0xFF7C4DFF), unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        if (mensagemErro.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(mensagemErro, color = Color(0xFFFF5252), fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val valorAdicional = valorAAjustarStr.toDoubleOrNull() ?: 0.0
                val meta = metaOriginal

                if (valorAdicional <= 0.0) {
                    mensagemErro = "Insira um valor maior que zero!"
                    return@Button
                }

                if (meta != null) {
                    val novoValorAtual = meta.valorAtual + valorAdicional

                    viewModel.adicionarMeta(
                        MetaEntity(
                            id = meta.id,
                            userId = meta.userId,
                            objetivo = meta.objetivo,
                            valorAlvo = meta.valorAlvo,
                            valorAtual = novoValorAtual,
                            prazo = meta.prazo
                        )
                    )
                    navController.popBackStack()
                } else {
                    mensagemErro = "Aguardando carregamento da meta..."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C4DFF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Confirmar Depósito", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}