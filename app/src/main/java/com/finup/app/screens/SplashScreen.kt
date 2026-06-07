package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.*

@Composable
fun SplashScreen(
    navController: NavController? = null
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "FinUp!",
            style = MaterialTheme.typography.headlineLarge
        )
    }

    // FUTURO: navegação automática (se quiser ativar)
    /*
    LaunchedEffect(true) {
        delay(1500)
        navController?.navigate("login") {
            popUpTo(0)
        }
    }
    */
}