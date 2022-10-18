package com.github.plplmax.poke.detail.data.storage

import com.github.plplmax.poke.detail.data.storage.remote.RemotePokemonDetailStorage
import com.github.plplmax.poke.detail.domain.model.PokemonDetail
import com.github.plplmax.poke.detail.domain.storage.PokemonDetailStorage
import com.github.plplmax.poke.di.app.AppModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDetailStorageOf @Inject constructor(
    private val remote: RemotePokemonDetailStorage,
    @AppModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : PokemonDetailStorage {
    override suspend fun fetch(id: Int): Result<PokemonDetail> {
        return withContext(ioDispatcher) {
            kotlin.runCatching { remote.fetch(id).transform() }
        }
    }
}
