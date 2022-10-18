package com.github.plplmax.poke.main.domain.model

data class Pokemon(val url: String, val name: String) {
    val id: Int = url.split('/').dropLast(1).last().toInt()
    val imageUrl: String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}
