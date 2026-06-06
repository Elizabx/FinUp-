package com.finup.app.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Cadastro : Routes("cadastro")
    object Dashboard : Routes("dashboard")
    object Meta : Routes("meta")
    object Perfil : Routes("perfil")
    object Relatorio : Routes("relatorio")
    object AddTransaction : Routes("addTransaction")
    object AddMeta : Routes("addMeta")

    object EditTransaction : Routes("edit_transaction/{id}") {
        fun createRoute(id: Int) = "edit_transaction/$id"
    }
}