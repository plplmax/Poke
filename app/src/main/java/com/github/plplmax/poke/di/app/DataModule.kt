package com.github.plplmax.poke.di.app

import com.github.plplmax.poke.core.moshi.JsonObjectConversion
import com.github.plplmax.poke.detail.data.storage.PokemonDetailStorageOf
import com.github.plplmax.poke.detail.data.storage.remote.PokemonDetailApi
import com.github.plplmax.poke.detail.data.storage.remote.RemotePokemonDetailStorage
import com.github.plplmax.poke.detail.data.storage.remote.RemotePokemonDetailStorageOf
import com.github.plplmax.poke.detail.domain.storage.PokemonDetailStorage
import com.github.plplmax.poke.main.data.storage.PokemonStorageOf
import com.github.plplmax.poke.main.data.storage.remote.PokemonApi
import com.github.plplmax.poke.main.data.storage.remote.RemotePokemonStorage
import com.github.plplmax.poke.main.data.storage.remote.RemotePokemonStorageOf
import com.github.plplmax.poke.main.domain.storage.PokemonStorage
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
interface DataModule {
    @Binds
    @Singleton
    fun bindPokemonStorage(storage: PokemonStorageOf): PokemonStorage

    @Binds
    @Singleton
    fun bindPokemonDetailStorage(storage: PokemonDetailStorageOf): PokemonDetailStorage

    @Binds
    @Singleton
    fun bindRemotePokemonStorage(storage: RemotePokemonStorageOf): RemotePokemonStorage

    @Binds
    @Singleton
    fun bindRemotePokemonDetailStorage(storage: RemotePokemonDetailStorageOf): RemotePokemonDetailStorage

    companion object {
        @Provides
        @Singleton
        fun providePokemonApi(retrofit: Retrofit): PokemonApi {
            return retrofit.create(PokemonApi::class.java)
        }

        @Provides
        @Singleton
        fun providePokemonDetailApi(retrofit: Retrofit): PokemonDetailApi {
            return retrofit.create(PokemonDetailApi::class.java)
        }

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder().add(JsonObjectConversion()).build()
                    )
                )
                .build()
        }
    }
}
