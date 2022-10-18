package com.github.plplmax.poke.di.app

import com.github.plplmax.poke.detail.domain.usecase.FetchPokemon
import com.github.plplmax.poke.detail.domain.usecase.FetchPokemonOf
import com.github.plplmax.poke.main.domain.usecase.FetchPokemons
import com.github.plplmax.poke.main.domain.usecase.FetchPokemonsOf
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {
    @Binds
    @Singleton
    fun bindFetchPokemons(useCase: FetchPokemonsOf): FetchPokemons

    @Binds
    @Singleton
    fun bindFetchPokemon(useCase: FetchPokemonOf): FetchPokemon
}
