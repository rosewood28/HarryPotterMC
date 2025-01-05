package com.example.harrypottermc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.harrypottermc.model.HpCharacter
import com.example.harrypottermc.model.StringListConverter

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [HpCharacter::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class HpCharactersDatabase : RoomDatabase() {

    abstract fun hpCharacterDao(): HpCharacterDao

    companion object {
        @Volatile
        private var Instance: HpCharactersDatabase? = null

        fun getDatabase(context: Context): HpCharactersDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HpCharactersDatabase::class.java, "character_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
