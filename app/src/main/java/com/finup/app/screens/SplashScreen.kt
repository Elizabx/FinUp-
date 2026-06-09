package com.finup.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(true) {
        kotlinx.coroutines.delay(1200)
        navController.navigate("login") {
            popUpTo(0)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("FinUp", style = MaterialTheme.typography.headlineLarge)
    }
}