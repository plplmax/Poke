package com.github.plplmax.poke.main.data.storage.remote

import com.github.plplmax.poke.main.data.storage.remote.response.PokemonsWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun fetch(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonsWrapperResponse
}
