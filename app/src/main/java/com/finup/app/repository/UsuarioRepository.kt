package com.finup.app.repository

import com.finup.app.database.entity.UsuarioEntity

interface UsuarioRepository {
    suspend fun buscarPorEmail(email: String): UsuarioEntity?
    suspend fun login(email: String, senha: String): UsuarioEntity? // <-- Define o nome oficial como 'login'
    suspend fun buscarPorId(id: Int): UsuarioEntity?
    suspend fun cadastrar(usuario: UsuarioEntity)
}