package com.finup.app.repository

import com.finup.app.database.dao.UsuarioDao
import com.finup.app.database.entity.UsuarioEntity

class UsuarioRepositoryImpl(private val usuarioDao: UsuarioDao) : UsuarioRepository {

    override suspend fun buscarPorEmail(email: String): UsuarioEntity? {
        return usuarioDao.buscarPorEmail(email)
    }

    override suspend fun login(email: String, senha: String): UsuarioEntity? {
        return usuarioDao.loginComSenha(email, senha)
    }

    override suspend fun buscarPorId(id: Int): UsuarioEntity? = usuarioDao.getUsuarioByIdNormal(id)

    override suspend fun cadastrar(usuario: UsuarioEntity) = usuarioDao.insertUsuario(usuario)
}