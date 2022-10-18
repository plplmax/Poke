package com.github.plplmax.poke.main.data.storage.remote.response

import com.github.plplmax.poke.main.data.model.PokemonData
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonsWrapperResponse(val results: List<PokemonData>)
