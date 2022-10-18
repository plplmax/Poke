package com.github.plplmax.poke.di.navigation

import androidx.fragment.app.FragmentManager
import com.github.plplmax.poke.core.navigation.AppNavigation
import com.github.plplmax.poke.core.navigation.Navigation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule(private val manager: FragmentManager) {
    @Provides
    @Singleton
    fun provideNavigation(): Navigation = AppNavigation(manager)
}
