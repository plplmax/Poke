package com.github.plplmax.poke.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.github.plplmax.poke.R
import com.github.plplmax.poke.core.navigation.Navigation
import com.github.plplmax.poke.di.navigation.DaggerNavigationComponent
import com.github.plplmax.poke.di.navigation.NavigationComponent
import com.github.plplmax.poke.di.navigation.NavigationModule

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    lateinit var navComponent: NavigationComponent
    private lateinit var nav: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        navComponent = DaggerNavigationComponent.builder()
            .navigationModule(NavigationModule(supportFragmentManager))
            .build()
        nav = navComponent.navigation()
        super.onCreate(savedInstanceState)
        savedInstanceState ?: nav.toMainScreen()
    }
}
