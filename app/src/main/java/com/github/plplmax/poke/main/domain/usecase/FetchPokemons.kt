package com.github.plplmax.poke.main.domain.usecase

import com.github.plplmax.poke.main.domain.model.Pokemon

interface FetchPokemons {
    suspend fun fetch(offset: Int, limit: Int = 20): Result<List<Pokemon>>
}
