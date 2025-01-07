package com.example.harrypottermc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.harrypottermc.ui.HPApp
import com.example.harrypottermc.ui.theme.HarryPotterMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HarryPotterMCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    /*HarryPotterApp()*/
                    HPApp()
                }
            }
        }
    }
}