package com.finup.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun AddMetaScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val userId = sessionManager.getUserId() ?: -1

    val viewModel: MetaViewModel = viewModel(factory = AppViewModelProvider.Factory)

    var objetivo by remember { mutableStateOf("") }
    var valorAlvoStr by remember { mutableStateOf("") }
    var valorAtualStr by remember { mutableStateOf("") }
    var prazo by remember { mutableStateOf("") }
    var mensagemErro by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F111A))
            .padding(24.dp)
    ) {
        Text("Criar Nova Meta", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Defina seu próximo objetivo financeiro", color = Color(0xFF8E94A6), fontSize = 14.sp)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = objetivo,
            onValueChange = { objetivo = it },
            label = { Text("Objetivo (Ex: Viagem, Carro)", color = Color(0xFF8E94A6)) },
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
            value = valorAlvoStr,
            onValueChange = { valorAlvoStr = it },
            label = { Text("Valor Total Alvo (R$)", color = Color(0xFF8E94A6)) },
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
            value = valorAtualStr,
            onValueChange = { valorAtualStr = it },
            label = { Text("Quanto já tem guardado? (R$)", color = Color(0xFF8E94A6)) },
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
            value = prazo,
            onValueChange = { prazo = it },
            label = { Text("Prazo limite (Ex: 12 meses)", color = Color(0xFF8E94A6)) },
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
                val valorAlvo = valorAlvoStr.toDoubleOrNull() ?: 0.0
                val valorAtual = valorAtualStr.toDoubleOrNull() ?: 0.0

                if (objetivo.isBlank() || valorAlvo <= 0.0 || prazo.isBlank()) {
                    mensagemErro = "Preencha todos os campos corretamente!"
                    return@Button
                }

                if (userId != -1) {
                    viewModel.adicionarMeta(
                        MetaEntity(
                            id = 0,
                            userId = userId,
                            objetivo = objetivo,
                            valorAlvo = valorAlvo,
                            valorAtual = valorAtual,
                            prazo = prazo
                        )
                    )
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C4DFF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Salvar Meta", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}