package com.github.plplmax.poke.main.domain.storage

import com.github.plplmax.poke.main.domain.model.Pokemon

interface PokemonStorage {
    suspend fun fetch(offset: Int, limit: Int): Result<List<Pokemon>>
}
