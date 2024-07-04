package com.example.testovoeplanetickets.main_ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.testovoeplanetickets.ui.theme.BasicBlack
import com.example.testovoeplanetickets.ui.theme.SpecialBlue


@Composable
fun BottomNavigationBar(navController: NavHostController)
{
    NavigationBar(containerColor = BasicBlack) {
        val items = listOf(
            Routes.NavBar.PlaneScreen,
            Routes.NavBar.HotelScreen,
            Routes.NavBar.SmallerScreen,
            Routes.NavBar.SubscribeScreen,
            Routes.NavBar.ProfileScreen
        )

        val currentRoute = navController.currentBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route)},
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription ="",Modifier.size(24.dp) ) },
                label = { Text(text = item.title, fontSize = 10.sp)},
                colors = NavigationBarItemColors(
                    selectedIconColor = SpecialBlue,
                    selectedTextColor = SpecialBlue,
                    selectedIndicatorColor = BasicBlack,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    disabledIconColor = Color.Gray,
                    disabledTextColor = Color.Gray)
            )
        }
    }

}