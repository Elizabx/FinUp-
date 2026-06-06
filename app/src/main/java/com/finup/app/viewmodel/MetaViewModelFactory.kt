package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finup.app.repository.MetaRepository

class MetaViewModelFactory(
    private val metaRepository: MetaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MetaViewModel::class.java)) {
            return MetaViewModel(metaRepository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido")
    }
}