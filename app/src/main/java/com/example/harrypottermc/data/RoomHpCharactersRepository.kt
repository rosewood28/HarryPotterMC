package com.example.harrypottermc.data

import com.example.harrypottermc.model.HpCharacter
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [HpCharacter] from a given data source.
 */
interface RoomHpCharactersRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllHpCharactersStream(): Flow<List<HpCharacter>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getHpCharacterStream(id: Int): Flow<HpCharacter?>

    /**
     * Insert item in the data source
     */
    suspend fun insertHpCharacter(item: HpCharacter)

    /**
     * Delete item from the data source
     */
    suspend fun deleteHpCharacter(item: HpCharacter)

    /**
     * Update item in the data source
     */
    suspend fun updateHpCharacter(item: HpCharacter)
}
