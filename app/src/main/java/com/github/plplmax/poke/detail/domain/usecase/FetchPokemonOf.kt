package com.github.plplmax.poke.detail.domain.usecase

import com.github.plplmax.poke.detail.domain.model.PokemonDetail
import com.github.plplmax.poke.detail.domain.storage.PokemonDetailStorage
import javax.inject.Inject

class FetchPokemonOf @Inject constructor(private val storage: PokemonDetailStorage) : FetchPokemon {
    override suspend fun fetch(id: Int): Result<PokemonDetail> {
        return storage.fetch(id)
    }
}
