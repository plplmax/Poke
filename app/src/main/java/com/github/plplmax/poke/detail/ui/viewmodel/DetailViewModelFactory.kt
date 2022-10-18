package com.github.plplmax.poke.detail.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.plplmax.poke.detail.domain.usecase.FetchPokemon
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(
    private val useCase: FetchPokemon
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModelOf(useCase) as T
    }
}
