package com.finup.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.finup.app.database.entity.TransactionEntity
import com.finup.app.di.AppViewModelProvider
import com.finup.app.session.SessionManager
import com.finup.app.viewmodel.TransactionViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val userId = sessionManager.getUserId() ?: -1

    val viewModel: TransactionViewModel = viewModel(factory = AppViewModelProvider.Factory)

    var descricao by remember { mutableStateOf("") }
    var valorStr by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("SAIDA") }
    var categoria by remember { mutableStateOf("Geral") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F111A))
            .padding(24.dp)
    ) {
        Text("Nova Movimentação", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Card(
                modifier = Modifier.weight(1f).clickable { tipo = "ENTRADA" },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (tipo == "ENTRADA") Color(0xFF00E676).copy(alpha = 0.2f) else Color(0xFF161926)
                )
            ) {
                Box(Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                    Text("Entrada (+)", color = if (tipo == "ENTRADA") Color(0xFF00E676) else Color(0xFF8E94A6), fontWeight = FontWeight.Bold)
                }
            }

            Card(
                modifier = Modifier.weight(1f).clickable { tipo = "SAIDA" },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (tipo == "SAIDA") Color(0xFFFF5252).copy(alpha = 0.2f) else Color(0xFF161926)
                )
            ) {
                Box(Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                    Text("Saída (-)", color = if (tipo == "SAIDA") Color(0xFFFF5252) else Color(0xFF8E94A6), fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição", color = Color(0xFF8E94A6)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF161926), unfocusedContainerColor = Color(0xFF161926),
                focusedBorderColor = Color(0xFF7C4DFF), unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = valorStr,
            onValueChange = { valorStr = it },
            label = { Text("Valor (R$)", color = Color(0xFF8E94A6)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF161926), unfocusedContainerColor = Color(0xFF161926),
                focusedBorderColor = Color(0xFF7C4DFF), unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoria", color = Color(0xFF8E94A6)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF161926), unfocusedContainerColor = Color(0xFF161926),
                focusedBorderColor = Color(0xFF7C4DFF), unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val valor = valorStr.toDoubleOrNull() ?: 0.0
                if (descricao.isNotBlank() && valor > 0.0 && userId != -1) {
                    val dataAtual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                    viewModel.adicionarTransacao(
                        TransactionEntity(
                            id = 0,
                            userId = userId,
                            descricao = descricao,
                            valor = valor,
                            tipo = tipo,
                            categoria = categoria,
                            data = dataAtual
                        )
                    )
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C4DFF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Confirmar Transação", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}