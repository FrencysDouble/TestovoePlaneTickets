package com.example.testovoeplanetickets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.testovoeplanetickets.di.MyApp
import com.example.testovoeplanetickets.main_ui.navigation.MainNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val coreSupervisor = (application as MyApp).cs

            val controllers = with(coreSupervisor.ApplicationMain())
            {
                getApplicationControllers()
            }
            MainNavigation(controllers)
        }
    }
}