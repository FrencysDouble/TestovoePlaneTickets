package com.example.testovoeplanetickets.main_ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.testovoeplanetickets.main_ui.navigation.BottomNavigationBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SmallerScreen(navController: NavHostController)
{
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController = navController) }) {
    }
}