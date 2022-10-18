package com.github.plplmax.poke.main.data.storage

import com.github.plplmax.poke.di.app.AppModule
import com.github.plplmax.poke.main.data.model.PokemonData
import com.github.plplmax.poke.main.data.storage.remote.RemotePokemonStorage
import com.github.plplmax.poke.main.domain.model.Pokemon
import com.github.plplmax.poke.main.domain.storage.PokemonStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonStorageOf @Inject constructor(
    private val remote: RemotePokemonStorage,
    @AppModule.IoDispatcher
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonStorage {
    override suspend fun fetch(offset: Int, limit: Int): Result<List<Pokemon>> {
        return withContext(dispatcher) {
            kotlin.runCatching { remote.fetch(offset, limit).map(PokemonData::transform) }
        }
    }
}
