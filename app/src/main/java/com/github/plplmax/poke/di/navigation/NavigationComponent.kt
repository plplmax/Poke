package com.github.plplmax.poke.di.navigation

import com.github.plplmax.poke.core.navigation.Navigation
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent {
    fun navigation(): Navigation
}
