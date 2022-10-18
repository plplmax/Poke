package com.github.plplmax.poke.detail.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.plplmax.poke.detail.domain.model.PokemonDetail

interface DetailViewModel {
    fun fetch(id: Int)
    fun observeSuccess(owner: LifecycleOwner, observer: Observer<PokemonDetail>)
    fun observeFailure(owner: LifecycleOwner, observer: Observer<String>)
    fun observeLoading(owner: LifecycleOwner, observer: Observer<Boolean>)
}
