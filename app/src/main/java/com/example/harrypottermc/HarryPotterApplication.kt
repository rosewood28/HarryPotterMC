package com.example.harrypottermc

import android.app.Application
import com.example.harrypottermc.data.AppContainer
import com.example.harrypottermc.data.DefaultAppContainer

class HarryPotterApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}