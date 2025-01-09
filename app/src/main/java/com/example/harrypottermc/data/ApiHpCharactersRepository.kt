package com.example.harrypottermc.data

import com.example.harrypottermc.model.HpCharacter

/**
 * Repository that fetches character data list from HP-API.
 */
interface ApiHpCharactersRepository {
    suspend fun getHpCharactersFromApi(): List<HpCharacter>
}
