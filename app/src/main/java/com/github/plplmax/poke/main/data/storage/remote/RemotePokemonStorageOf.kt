package com.github.plplmax.poke.main.data.storage.remote

import com.github.plplmax.poke.main.data.model.PokemonData
import javax.inject.Inject

class RemotePokemonStorageOf @Inject constructor(private val api: PokemonApi) :
    RemotePokemonStorage {
    override suspend fun fetch(offset: Int, limit: Int): List<PokemonData> {
        return api.fetch(offset, limit).results
    }
}
