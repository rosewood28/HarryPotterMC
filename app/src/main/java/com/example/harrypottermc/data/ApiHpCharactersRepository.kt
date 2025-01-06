package com.example.harrypottermc.data

import com.example.harrypottermc.model.HpCharacter

/**
 * Repository that fetch character data list from HP-API.
 */
interface ApiHpCharactersRepository {
    suspend fun getHpCharactersFromApi(): List<HpCharacter>
}
//
///**
// * Network Implementation of Repository that fetches character data list from HP-API.
// */
//class NetworkHpCharactersRepository (
//    private val hpApiService: HPApiService
//) : ApiHpCharactersRepository {
//    /** Fetches list of HpCharacters from HP-API*/
//    override suspend fun getHpCharacters(): List<HpCharacter> = hpApiService.getHpCharacters()
//}