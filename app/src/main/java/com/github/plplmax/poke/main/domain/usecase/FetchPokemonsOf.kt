package com.github.plplmax.poke.main.domain.usecase

import com.github.plplmax.poke.main.domain.model.Pokemon
import com.github.plplmax.poke.main.domain.storage.PokemonStorage
import javax.inject.Inject

class FetchPokemonsOf @Inject constructor(private val storage: PokemonStorage) : FetchPokemons {
    override suspend fun fetch(offset: Int, limit: Int): Result<List<Pokemon>> {
        return storage.fetch(offset, limit)
    }
}
