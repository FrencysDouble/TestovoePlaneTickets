package com.example.testovoeplanetickets.main_ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testovoeplanetickets.main_ui.PlaneScreen
import com.example.testovoeplanetickets.main_ui.SearchResult
import com.example.testovoeplanetickets.main_ui.SearchScreen
import com.example.testovoeplanetickets.di.ControllersModule
import com.example.testovoeplanetickets.main_ui.HotelScreen
import com.example.testovoeplanetickets.main_ui.ProfileScreen
import com.example.testovoeplanetickets.main_ui.SmallerScreen
import com.example.testovoeplanetickets.main_ui.SubscribeButton


@Composable
fun MainNavigation(controllers: ControllersModule)
{
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.NavBar.PlaneScreen.route)
    {
        composable (Routes.NavBar.PlaneScreen.route)
        {
            PlaneScreen(navController = navController,controllers.providePlaneController(),controllers.provideSelectedText())
        }
        composable (Routes.NavBar.HotelScreen.route)
        {
            HotelScreen(navController)
        }
        composable (Routes.NavBar.SmallerScreen.route)
        {
            SmallerScreen(navController)
        }
        composable (Routes.NavBar.SubscribeScreen.route)
        {
            SubscribeButton(navController)
        }
        composable (Routes.NavBar.ProfileScreen.route)
        {
            ProfileScreen(navController)
        }
        composable (Routes.Screens.SearchScreen.route)
        {
            SearchScreen(navController = navController,controllers.provideSelectedText(),controllers.provideCalendarController(),controllers.provideSearchScreenController())
        }
        composable (Routes.Screens.SearchScreenResult.route)
        {
            SearchResult(navController = navController,controllers.provideSearchScreenController(),controllers.provideSelectedText())
        }
    }
}