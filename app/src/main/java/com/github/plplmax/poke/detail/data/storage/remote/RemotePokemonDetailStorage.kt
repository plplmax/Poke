package com.github.plplmax.poke.detail.data.storage.remote

import com.github.plplmax.poke.detail.data.model.PokemonDetailData

interface RemotePokemonDetailStorage {
    suspend fun fetch(id: Int): PokemonDetailData
}
