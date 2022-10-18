package com.github.plplmax.poke.main.data.storage.remote

import com.github.plplmax.poke.main.data.model.PokemonData

interface RemotePokemonStorage {
    suspend fun fetch(offset: Int, limit: Int): List<PokemonData>
}
