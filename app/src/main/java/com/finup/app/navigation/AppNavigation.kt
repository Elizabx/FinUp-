package com.finup.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.finup.app.screens.*

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {
            LoginScreen(navController)
        }

        composable(Routes.Cadastro.route) {
            CadastroScreen(navController)
        }

        composable(Routes.Dashboard.route) {
            DashboardScreen(navController)
        }

        composable(Routes.Meta.route) {
            MetaScreen(navController)
        }

        composable(Routes.Perfil.route) {
            PerfilScreen(navController)
        }

        composable(Routes.Relatorio.route) {
            RelatorioScreen(navController)
        }

        composable(Routes.AddTransaction.route) {
            AddTransactionScreen(navController)
        }
    }
}