package com.example.harrypottermc

import android.app.Application
import com.example.harrypottermc.data.AppContainer
import com.example.harrypottermc.data.DefaultAppContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HarryPotterApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)

        // Populate database with API data
        CoroutineScope(Dispatchers.IO).launch {
            container.hpCharactersRepository.refreshCharacters()
        }
    }
}