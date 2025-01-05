package com.example.harrypottermc.data

import android.content.Context
import com.example.harrypottermc.network.HPApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val apiHpCharactersRepository: ApiHpCharactersRepository
    val roomHpCharactersRepository: RoomHpCharactersRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer(private val context: Context) : AppContainer {

    // Room setup
    private val hpCharactersDatabase: HpCharactersDatabase by lazy {
        HpCharactersDatabase.getDatabase(context)
    }

    private val hpCharacterDao = hpCharactersDatabase.hpCharacterDao()

    override val roomHpCharactersRepository: RoomHpCharactersRepository by lazy {
        OfflineHpCharactersRepository(hpCharacterDao)
    }

    //Retrofit setup
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
     * DI implementation for HpCharacters repository
     */
    override val apiHpCharactersRepository: ApiHpCharactersRepository by lazy {
        NetworkHpCharactersRepository(retrofitService)
    }


}
