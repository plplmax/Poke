package com.github.plplmax.poke.di.app

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
interface AppModule {
    companion object {
        @Provides
        @Singleton
        @IoDispatcher
        fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class IoDispatcher
}
