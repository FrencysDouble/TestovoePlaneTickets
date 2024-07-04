package com.example.testovoeplanetickets.main_ui.navigation

import com.example.testovoeplanetickets.R

object Routes {

    object NavBar
    {
        object PlaneScreen : BottomNavItem
            (
                    "Авиабилеты",
                    R.drawable.navbar_plane,
                    "plane_screen"
                    )

        object HotelScreen : BottomNavItem
            (
                    "Отели",
                    R.drawable.hotel_icon,
                    "hotel_screen"
                    )

        object SmallerScreen : BottomNavItem
            (
                    "Короче",
                    R.drawable.gps_icon,
                    "smaller_screen"
                    )

        object SubscribeScreen : BottomNavItem
            (
                    "Подписки",
                    R.drawable.subs_icon,
                    "subs_screen"
                    )
        object ProfileScreen : BottomNavItem
            (
                    "Профиль",
                    R.drawable.profile_icon,
                    "profile_screen"
                    )
    }

    object Screens
    {
        object SearchScreen : Screen ("search_screen")

        object SearchScreenResult : Screen ("search_screen_result")

        object BottomSheetDialog : Screen ("bottom_sheet_dialog")
    }
}



sealed class BottomNavItem(
    val title: String,
    val icon: Int,
    val route: String
)

sealed class Screen(val route : String)