package com.example.harrypottermc.data

import com.example.harrypottermc.network.HPApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val charactersRepository: CharactersRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://hp-api.herokuapp.com/"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: HPApiService by lazy {
        retrofit.create(HPApiService::class.java)
    }

    /**
     * DI implementation for Characters repository
     */
    override val charactersRepository: CharactersRepository by lazy {
        NetworkCharactersRepository(retrofitService)
    }
}