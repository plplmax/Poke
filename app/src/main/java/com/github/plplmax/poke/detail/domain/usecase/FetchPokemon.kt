package com.github.plplmax.poke.detail.domain.usecase

import com.github.plplmax.poke.detail.domain.model.PokemonDetail

interface FetchPokemon {
    suspend fun fetch(id: Int): Result<PokemonDetail>
}
