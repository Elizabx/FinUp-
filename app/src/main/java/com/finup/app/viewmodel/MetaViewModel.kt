package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import com.finup.app.model.MetaFinanceira
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MetaViewModel : ViewModel() {

    private val _metas = MutableStateFlow<List<MetaFinanceira>>(emptyList())
    val metas: StateFlow<List<MetaFinanceira>> = _metas

    fun adicionarMeta(meta: MetaFinanceira) {
        _metas.value = _metas.value + meta
    }

    fun removerMeta(id: Int) {
        _metas.value = _metas.value.filter { it.id != id }
    }

    fun atualizarMeta(metaAtualizada: MetaFinanceira) {
        _metas.value = _metas.value.map {
            if (it.id == metaAtualizada.id) metaAtualizada else it
        }
    }

    fun adicionarValor(id: Int, valor: Double) {
        _metas.value = _metas.value.map { meta ->
            if (meta.id == id) {
                meta.copy(valorAtual = meta.valorAtual + valor)
            } else meta
        }
    }
}