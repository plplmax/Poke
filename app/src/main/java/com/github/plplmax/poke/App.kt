package com.github.plplmax.poke

import android.app.Application
import com.github.plplmax.poke.di.app.AppComponent
import com.github.plplmax.poke.di.app.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
