package com.finup.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.finup.app.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController = navController) }
        composable("cadastro") { CadastroScreen(navController = navController) }
        composable("dashboard") { DashboardScreen(navController = navController) }
        composable("add_transaction") { AddTransactionScreen(navController = navController) }
        composable("extrato") { ExtratoScreen(navController = navController) }
        composable("relatorio") { RelatorioScreen(navController = navController) }
        composable("perfil") { PerfilScreen(navController = navController) }
        composable("meta") { MetaScreen(navController = navController) }
        composable("add_meta") { AddMetaScreen(navController = navController) }

        composable(
            route = "edit_meta/{metaId}",
            arguments = listOf(navArgument("metaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val metaId = backStackEntry.arguments?.getInt("metaId") ?: -1
            EditMetaScreen(navController = navController, metaId = metaId)
        }
    }
}