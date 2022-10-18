package com.github.plplmax.poke.di.app

import com.github.plplmax.poke.detail.ui.viewmodel.DetailViewModelFactory
import com.github.plplmax.poke.main.ui.viewmodel.MainViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun mainViewModelFactory(): MainViewModelFactory
    fun detailViewModelFactory(): DetailViewModelFactory
}
