package com.github.plplmax.poke.detail.data.storage.remote

import com.github.plplmax.poke.detail.data.model.PokemonDetailData
import javax.inject.Inject

class RemotePokemonDetailStorageOf @Inject constructor(private val api: PokemonDetailApi) :
    RemotePokemonDetailStorage {
    override suspend fun fetch(id: Int): PokemonDetailData {
        val result = api.fetch(id)
        val imageUrl = result.getJSONObject("sprites")
            .getJSONObject("other")
            .getJSONObject("official-artwork")
            .getString("front_default")
        val stats = result.getJSONArray("stats")
        return PokemonDetailData(
            result.getInt("id"),
            result.getString("name"),
            imageUrl,
            result.getDouble("weight"),
            result.getDouble("height"),
            stats.getJSONObject(0).getInt("base_stat"),
            stats.getJSONObject(1).getInt("base_stat"),
            stats.getJSONObject(2).getInt("base_stat"),
            stats.getJSONObject(5).getInt("base_stat"),
            result.getInt("base_experience")
        )
    }
}
