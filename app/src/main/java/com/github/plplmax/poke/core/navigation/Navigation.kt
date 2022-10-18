package com.github.plplmax.poke.core.navigation

interface Navigation {
    fun toMainScreen()
    fun toDetailScreen(id: Int)
    fun popBackStack()
}
