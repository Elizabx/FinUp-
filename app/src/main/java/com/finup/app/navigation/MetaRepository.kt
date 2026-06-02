package com.finup.app.repository

import com.finup.app.model.MetaFinanceira

class MetaRepository {

    private val lista = mutableListOf<MetaFinanceira>()

    fun inserir(meta: MetaFinanceira) {
        lista.add(meta)
    }

    fun listarTodas(): List<MetaFinanceira> {
        return lista
    }

    fun atualizar(meta: MetaFinanceira) {
        val index = lista.indexOfFirst { it.id == meta.id }
        if (index != -1) {
            lista[index] = meta
        }
    }

    fun deletar(meta: MetaFinanceira) {
        lista.removeIf { it.id == meta.id }
    }
}