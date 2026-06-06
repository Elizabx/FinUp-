package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finup.app.database.entity.MetaEntity
import com.finup.app.repository.MetaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MetaViewModel(
    private val repository: MetaRepository
) : ViewModel() {

    val metas = repository
        .listarTodas()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun adicionarMeta(
        meta: MetaEntity
    ) {
        viewModelScope.launch {
            repository.inserir(meta)
        }
    }

    fun removerMeta(
        meta: MetaEntity
    ) {
        viewModelScope.launch {
            repository.deletar(meta)
        }
    }

    fun atualizarMeta(
        meta: MetaEntity
    ) {
        viewModelScope.launch {
            repository.atualizar(meta)
        }
    }

    fun adicionarValor(
        id: Int,
        valor: Double
    ) {
        viewModelScope.launch {

            val meta =
                metas.value.find { it.id == id }

            if (meta != null) {

                val metaAtualizada =
                    meta.copy(
                        valorAtual =
                            meta.valorAtual + valor
                    )

                repository.atualizar(
                    metaAtualizada
                )
            }
        }
    }
}