package com.github.plplmax.poke.main.ui.viewmodel

import androidx.lifecycle.*
import com.github.plplmax.poke.main.domain.model.Pokemon
import com.github.plplmax.poke.main.domain.usecase.FetchPokemons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModelOf(private val useCase: FetchPokemons) : ViewModel(), MainViewModel {
    private val pokemons: MutableLiveData<List<Pokemon>> = MutableLiveData(listOf())
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val error: MutableLiveData<String> = MutableLiveData("")
    private var currentItems: Int = 0
    private var job: Job? = null

    init {
        fetchNext()
    }

    override fun observePokemons(owner: LifecycleOwner, observer: Observer<List<Pokemon>>) {
        pokemons.observe(owner, observer)
    }

    override fun observeLoading(owner: LifecycleOwner, observer: Observer<Boolean>) {
        isLoading.observe(owner, observer)
    }

    override fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
        error.observe(owner, observer)
    }

    override fun errorExists(): Boolean = error.value!!.isNotEmpty()

    override fun fetchNext() {
        if (job != null) {
            return
        }
        enableLoading()
        clearError()
        job = viewModelScope.launch(Dispatchers.Main) {
            val result = useCase.fetch(currentItems)
            result.onSuccess {
                currentItems += it.size
                pokemons.value = pokemons.value!! + it
            }
            result.onFailure {
                error.value = it.message ?: "Something went wrong"
                pokemons.value = pokemons.value
            }
            disableLoading()
            job = null
        }
    }

    private fun enableLoading() {
        isLoading.value = true
    }

    private fun disableLoading() {
        isLoading.value = false
    }

    override fun clearError() {
        error.value = ""
    }
}
