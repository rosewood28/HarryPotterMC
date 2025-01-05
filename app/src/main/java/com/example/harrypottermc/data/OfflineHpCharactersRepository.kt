package com.example.harrypottermc.data

import kotlinx.coroutines.flow.Flow
import com.example.harrypottermc.model.HpCharacter

class OfflineHpCharactersRepository (private val hpCharacterDao: HpCharacterDao) : RoomHpCharactersRepository {
    override fun getAllHpCharactersStream(): Flow<List<HpCharacter>> = hpCharacterDao.getAllHpCharacters()

    override fun getHpCharacterStream(id: Int): Flow<HpCharacter?> = hpCharacterDao.getHpCharacter(id)

    override suspend fun insertHpCharacter(item: HpCharacter) = hpCharacterDao.insert(item)

    override suspend fun deleteHpCharacter(item: HpCharacter) = hpCharacterDao.delete(item)

    override suspend fun updateHpCharacter(item: HpCharacter) = hpCharacterDao.update(item)
}
