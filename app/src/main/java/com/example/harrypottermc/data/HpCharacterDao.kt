package com.example.harrypottermc.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.harrypottermc.model.HpCharacter
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the HpCharacters database
 */
@Dao
interface HpCharacterDao {
    @Query("SELECT * from characters")
    fun getAllHpCharacters(): Flow<List<HpCharacter>>

    @Query("SELECT * from characters WHERE id = :id")
    fun getHpCharacter(id: String): Flow<HpCharacter>

    @Query("SELECT DISTINCT house FROM characters WHERE house IS NOT NULL ORDER BY house ASC")
    fun getAllHpHouses(): Flow<List<String>>

    @Query("SELECT * FROM characters WHERE house = :houseName")
    fun getHpCharacterByHouseName(houseName: String): Flow<List<HpCharacter>>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing HpCharacter into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: HpCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<HpCharacter>)

    @Update
    suspend fun update(character: HpCharacter)

    @Delete
    suspend fun delete(character: HpCharacter)
}
