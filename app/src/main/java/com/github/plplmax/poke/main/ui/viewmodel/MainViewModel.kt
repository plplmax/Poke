package com.github.plplmax.poke.main.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.plplmax.poke.main.domain.model.Pokemon

interface MainViewModel {
    fun observePokemons(owner: LifecycleOwner, observer: Observer<List<Pokemon>>)
    fun observeLoading(owner: LifecycleOwner, observer: Observer<Boolean>)
    fun observeError(owner: LifecycleOwner, observer: Observer<String>)
    fun errorExists(): Boolean
    fun fetchNext()
    fun clearError()
}
