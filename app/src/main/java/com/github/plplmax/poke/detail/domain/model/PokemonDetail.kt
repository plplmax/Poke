package com.github.plplmax.poke.detail.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weight: Double,
    val height: Double,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val experience: Int
)
