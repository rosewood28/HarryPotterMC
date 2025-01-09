package com.example.harrypottermc.network

import com.example.harrypottermc.model.HpCharacter
import retrofit2.http.GET

/**
 * A public interface that exposes the [getHpCharacters] method
 */
interface HpApiService {
    /**
     * Returns a [List] of [HpCharacter] and this method can be called from a Coroutine.
     */
    @GET("/api/characters")
    suspend fun getHpCharacters(): List<HpCharacter>
}