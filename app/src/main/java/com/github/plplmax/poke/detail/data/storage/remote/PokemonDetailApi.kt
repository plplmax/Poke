package com.github.plplmax.poke.detail.data.storage.remote

import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDetailApi {
    @GET("pokemon/{id}/")
    suspend fun fetch(@Path("id") id: Int): JSONObject
}
