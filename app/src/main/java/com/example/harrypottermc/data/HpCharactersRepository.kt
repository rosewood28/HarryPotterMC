package com.example.harrypottermc.data

import com.example.harrypottermc.model.HpCharacter
import com.example.harrypottermc.network.HPApiService
import kotlinx.coroutines.flow.Flow

class HpCharactersRepository (
    private val hpCharacterDao: HpCharacterDao,
    private val hpApiService: HPApiService
    ) : RoomHpCharactersRepository,  ApiHpCharactersRepository {
    /** Fetches list of HpCharacters from HP-API*/
    override suspend fun getHpCharactersFromApi(): List<HpCharacter> = hpApiService.getHpCharacters()

    // Room (OfflineRepository)
    suspend fun refreshCharacters() {
        // Fetch characters from API
        val apiCharacters = hpApiService.getHpCharacters()

        // Insert characters into Room
        hpCharacterDao.insertAll(apiCharacters)
    }

    suspend fun insertAllHpCharacters(characters: List<HpCharacter>) = hpCharacterDao.insertAll(characters)

    override fun getAllHpCharactersStream(): Flow<List<HpCharacter>> = hpCharacterDao.getAllHpCharacters()

    override fun getHpCharacterStream(id: String): Flow<HpCharacter?> = hpCharacterDao.getHpCharacter(id)

    override suspend fun insertHpCharacter(item: HpCharacter) = hpCharacterDao.insert(item)

    override suspend fun deleteHpCharacter(item: HpCharacter) = hpCharacterDao.delete(item)

    override suspend fun updateHpCharacter(item: HpCharacter) = hpCharacterDao.update(item)
}
///**
// * Repository that fetch character data list from HP-API.
// */
//interface ApiHpCharactersRepository {
//    suspend fun getHpCharactersFromApi(): List<HpCharacter>
//}

///**
// * Network Implementation of Repository that fetches character data list from HP-API.
// */
//class NetworkHpCharactersRepository (
//    private val hpApiService: HPApiService
//) : ApiHpCharactersRepository {
//    /** Fetches list of HpCharacters from HP-API*/
//    override suspend fun getHpCharacters(): List<HpCharacter> = hpApiService.getHpCharacters()
//}



