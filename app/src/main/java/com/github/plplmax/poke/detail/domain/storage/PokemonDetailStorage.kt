package com.github.plplmax.poke.detail.domain.storage

import com.github.plplmax.poke.detail.domain.model.PokemonDetail

interface PokemonDetailStorage {
    suspend fun fetch(id: Int): Result<PokemonDetail>
}
