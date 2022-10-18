package com.github.plplmax.poke.detail.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.github.plplmax.poke.detail.domain.model.PokemonDetail
import com.github.plplmax.poke.detail.domain.usecase.FetchPokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModelOf(private val useCase: FetchPokemon) : ViewModel(), DetailViewModel {
    private val success: MutableLiveData<PokemonDetail> = MutableLiveData()
    private val failure: MutableLiveData<String> = MutableLiveData()
    private val loading: MutableLiveData<Boolean> = MutableLiveData()

    override fun fetch(id: Int) {
        loading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val result = useCase.fetch(id)
            result.onSuccess { success.value = it }
            result.onFailure {
                Log.e("DetailViewModel", "fetch: $it")
                failure.value = it.message
            }
            loading.value = false
        }
    }

    override fun observeSuccess(owner: LifecycleOwner, observer: Observer<PokemonDetail>) {
        success.observe(owner, observer)
    }

    override fun observeFailure(owner: LifecycleOwner, observer: Observer<String>) {
        failure.observe(owner, observer)
    }

    override fun observeLoading(owner: LifecycleOwner, observer: Observer<Boolean>) {
        loading.observe(owner, observer)
    }
}
