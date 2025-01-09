package com.example.harrypottermc.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.harrypottermc.model.HpCharacter
import com.example.harrypottermc.network.HpApiService
import kotlinx.coroutines.flow.Flow

class HpCharactersRepository (
    private val hpCharacterDao: HpCharacterDao,
    private val hpApiService: HpApiService,
    private val context: Context,
    ) : RoomHpCharactersRepository,  ApiHpCharactersRepository {
    /** Fetches list of HpCharacters from HP-API*/
    override suspend fun getHpCharactersFromApi(): List<HpCharacter> = hpApiService.getHpCharacters()

    // Room (OfflineRepository)
    suspend fun refreshCharacters() {
        // Fetch characters from API
        if (!isOnline())
            return
        val apiCharacters = hpApiService.getHpCharacters()

        // Insert characters into Room
        hpCharacterDao.insertAll(apiCharacters)
    }

    override fun getAllHpCharactersStream(): Flow<List<HpCharacter>> = hpCharacterDao.getAllHpCharacters()

    fun getAllHousesNamesStream(): Flow<List<String>> = hpCharacterDao.getAllHpHouses()

    override fun getHpCharacterStream(id: String): Flow<HpCharacter?> = hpCharacterDao.getHpCharacter(id)

    fun getHpCharacterByHouseNameStream(houseName: String): Flow<List<HpCharacter>> =
        if (houseName == "Unassigned")
            hpCharacterDao.getHpCharacterByHouseName("")
        else
            hpCharacterDao.getHpCharacterByHouseName(houseName)


    override suspend fun insertHpCharacter(item: HpCharacter) = hpCharacterDao.insert(item)

    override suspend fun deleteHpCharacter(item: HpCharacter) = hpCharacterDao.delete(item)

    override suspend fun updateHpCharacter(item: HpCharacter) = hpCharacterDao.update(item)

    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}
