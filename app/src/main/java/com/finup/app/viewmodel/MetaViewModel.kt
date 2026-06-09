package com.finup.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finup.app.database.entity.MetaEntity
import com.finup.app.repository.MetaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class MetaViewModel(
    private val repository: MetaRepository
) : ViewModel() {

    private val userId = MutableStateFlow<Int?>(null)

    fun setUsuario(id: Int) {
        userId.value = id
    }

    val metas: StateFlow<List<MetaEntity>> = userId
        .filterNotNull()
        .flatMapLatest { repository.getMetasByUser(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun adicionarMeta(meta: MetaEntity) {
        viewModelScope.launch { repository.insert(meta) }
    }
}