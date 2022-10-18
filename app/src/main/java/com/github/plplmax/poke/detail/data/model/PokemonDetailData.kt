package com.github.plplmax.poke.detail.data.model

import com.github.plplmax.poke.core.Transformation
import com.github.plplmax.poke.detail.domain.model.PokemonDetail

data class PokemonDetailData(
    private val id: Int,
    private val name: String,
    private val imageUrl: String,
    private val weight: Double,
    private val height: Double,
    private val hp: Int,
    private val attack: Int,
    private val defense: Int,
    private val speed: Int,
    private val experience: Int
) : Transformation<PokemonDetail> {
    override fun transform(): PokemonDetail {
        return PokemonDetail(
            id,
            name,
            imageUrl,
            weight,
            height,
            hp,
            attack,
            defense,
            speed,
            experience
        )
    }
}
