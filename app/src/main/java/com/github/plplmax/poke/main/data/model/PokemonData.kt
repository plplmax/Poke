package com.github.plplmax.poke.main.data.model

import com.github.plplmax.poke.core.Transformation
import com.github.plplmax.poke.main.domain.model.Pokemon
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonData(val name: String, val url: String) : Transformation<Pokemon> {
    override fun transform(): Pokemon {
        return Pokemon(url, name)
    }
}
