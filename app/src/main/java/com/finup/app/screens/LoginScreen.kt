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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.finup.app.di.AppViewModelProvider
import com.finup.app.session.SessionManager
import com.finup.app.viewmodel.UsuarioViewModel

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: UsuarioViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val sessionManager = remember { SessionManager(context) }

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    var mensagemErro by remember { mutableStateOf("") }

    val usuarioLogado by viewModel.usuario.collectAsState()

    LaunchedEffect(usuarioLogado) {
        usuarioLogado?.let { usuario ->
            sessionManager.saveUserId(usuario.id)
            navController.navigate("dashboard") {
                popUpTo("login") { inclusive = true }
            }
        } ?: run {
            if (email.isNotEmpty() && senha.isNotEmpty()) {
                mensagemErro = "E-mail ou senha incorretos!"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F111A))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("FinUp!", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
        Text("Faça login para controlar suas finanças", color = Color(0xFF8E94A6), fontSize = 14.sp)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                mensagemErro = ""
            },
            label = { Text("E-mail", color = Color(0xFF8E94A6)) },
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
            value = senha,
            onValueChange = {
                senha = it
                mensagemErro = ""
            },
            label = { Text("Senha", color = Color(0xFF8E94A6)) },
            visualTransformation = PasswordVisualTransformation(),
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
            Text(
                text = mensagemErro,
                color = Color(0xFFFF5252),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                if (email.isBlank() || senha.isBlank()) {
                    mensagemErro = "Por favor, preencha todos os campos!"
                } else {
                    viewModel.login(email, senha)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C4DFF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Entrar", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Não tem uma conta? Cadastre-se",
            color = Color(0xFF7C4DFF),
            modifier = Modifier.clickable {
                navController.navigate("cadastro")
            },
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}