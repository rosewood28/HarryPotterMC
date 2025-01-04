package com.example.harrypottermc.network

import com.example.harrypottermc.model.Character
import retrofit2.http.GET

/**
 * A public interface that exposes the [getCharacters] method
 */
interface HPApiService {
    /**
     * Returns a [List] of [Characters] and this method can be called from a Coroutine.
     */
    @GET("/api/characters")
    suspend fun getCharacters(): List<Character>
}