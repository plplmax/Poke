package com.github.plplmax.poke.main.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.plplmax.poke.main.domain.usecase.FetchPokemons
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val useCase: FetchPokemons
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModelOf(useCase) as T
    }
}
