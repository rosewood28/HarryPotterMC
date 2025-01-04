package com.example.harrypottermc.data

import com.example.harrypottermc.model.Character
import com.example.harrypottermc.network.HPApiService

/**
 * Repository that fetch character data list from HP-API.
 */
interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
}

/**
 * Network Implementation of Repository that fetches character data list from HP-API.
 */
class NetworkCharactersRepository (
    private val hpApiService: HPApiService
) : CharactersRepository {
    /** Fetches list of Characters from HP-API*/
    override suspend fun getCharacters(): List<Character> = hpApiService.getCharacters()
}