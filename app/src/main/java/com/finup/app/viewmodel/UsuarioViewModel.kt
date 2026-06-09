package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finup.app.database.entity.UsuarioEntity
import com.finup.app.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {

    private val _usuario = MutableStateFlow<UsuarioEntity?>(null)
    val usuario: StateFlow<UsuarioEntity?> = _usuario

    fun login(email: String, senhaTexto: String) {
        viewModelScope.launch {
            _usuario.value = repository.login(email, senhaTexto)
        }
    }

    fun cadastrar(nome: String, email: String, senhaTexto: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val exists = repository.buscarPorEmail(email)
            if (exists != null) {
                onResult(false)
                return@launch
            }

            repository.cadastrar(
                UsuarioEntity(id = 0, nome = nome, email = email, senha = senhaTexto)
            )
            onResult(true)
        }
    }

    fun buscarUsuario(id: Int) {
        viewModelScope.launch {
            _usuario.value = repository.buscarPorId(id)
        }
    }

    fun logout() {
        _usuario.value = null
    }
}