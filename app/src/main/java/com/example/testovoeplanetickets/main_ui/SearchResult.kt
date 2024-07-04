package com.example.testovoeplanetickets.main_ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.testovoeplanetickets.R
import com.example.testovoeplanetickets.controllers.SearchScreenController
import com.example.testovoeplanetickets.controllers.SelectedText
import com.example.testovoeplanetickets.main_ui.navigation.BottomNavigationBar
import com.example.testovoeplanetickets.main_ui.navigation.Routes
import com.example.testovoeplanetickets.models.SearchResultModel
import com.example.testovoeplanetickets.ui.theme.BasicBlack
import com.example.testovoeplanetickets.ui.theme.BasicGrey
import com.example.testovoeplanetickets.ui.theme.BasicGrey5
import com.example.testovoeplanetickets.ui.theme.SpecialBlue


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchResult(
    navController: NavHostController,
    controller: SearchScreenController,
    selectedText: SelectedText
)
{
    LaunchedEffect(Unit)
    {
        controller.dataSearchLoad()
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController = navController) }) {
        ScreenR(controller,selectedText,navController)
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ScreenR(
    controller: SearchScreenController,
    selectedText: SelectedText,
    navController: NavHostController
)
{
    val liveSearchAllTicketsData = controller.liveSearchAllData.observeAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(BasicBlack)
            .padding(start = 12.dp, end = 12.dp, top = 48.dp)) {
        UpBar(selectedText,navController,controller)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp))
        liveSearchAllTicketsData.value?.let { MainSearchList(it) }


    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun UpBar(
    selectedText: SelectedText,
    navController: NavHostController,
    controller: SearchScreenController
)
{
    val upTextColor = Color.White
    val bottTextColor = BasicGrey5
    Row(
        Modifier
            .fillMaxWidth()
            .background(BasicGrey)
            , verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { navController.navigate(Routes.Screens.SearchScreen.route)}) {
            Icon(
                painter = painterResource(id = R.drawable.search_bar_reset_icon),
                contentDescription = "",
                tint = SpecialBlue
            )
        }
        Column(Modifier.padding(top =12.dp,bottom = 12.dp)) {
            Row {
                selectedText.selectedDestination.value?.let { Text(text = it,color = upTextColor, fontSize = 16.sp) }
                Text(text = "-",color = upTextColor, fontSize = 16.sp)
                selectedText.selectedApproach.value?.let { Text(text = it,color = upTextColor, fontSize = 16.sp) }
            }
            Row {
                Text(text = controller.getCurrentDate(),color = bottTextColor, fontSize = 14.sp, fontStyle = FontStyle.Italic)
                Text(text = " 1 пассажир" ,color = bottTextColor, fontSize = 14.sp, fontStyle = FontStyle.Italic)
            }
        }
    }
}

@Composable
private fun MainSearchList(liveSearchAllTicketsData: List<SearchResultModel>)
{
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 80.dp), verticalArrangement = Arrangement.spacedBy(24.dp))
    {
        items(liveSearchAllTicketsData) { item ->
            MainListItem(item)
        }
    }
}


@Composable
private fun MainListItem(item: SearchResultModel) {
    val price = "${item.price} ₽"
    val timeDep = item.departureTime
    val timeArr = item.arrivalTime
    val timeInRoad = item.timeInRoad
    val dep = item.departureAir
    val arr = item.arrivalAir

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .padding(top = 10.dp)
                .background(BasicGrey)
        ) {
            Column(Modifier.padding(12.dp)) {
                Text(text = price, fontSize = 22.sp, color = Color.White)
                Row(Modifier.padding(top = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.search_screen_list_rect_red),
                        contentDescription = "",
                        Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                    Column(Modifier.padding(start = 6.dp)) {
                        Row {
                            Text(
                                text = timeDep,
                                fontSize = 14.sp,
                                color = Color.White,
                                fontStyle = FontStyle.Italic
                            )
                            Spacer(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .width(12.dp)
                                    .height(1.dp)
                                    .background(BasicGrey5)
                            )
                            Text(
                                text = timeArr,
                                fontSize = 14.sp,
                                color = Color.White,
                                fontStyle = FontStyle.Italic
                            )
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(
                                text = timeInRoad + item.has_transfer,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        }
                        Row(Modifier.padding(top = 6.dp)) {
                            Text(text = dep, fontSize = 14.sp, color = BasicGrey5)
                            Spacer(modifier = Modifier.padding(start = 14.dp))
                            Text(text = arr, fontSize = 14.sp, color = BasicGrey5)
                        }
                    }
                }
            }
        }
        if (!item.badge.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = (-10).dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SpecialBlue)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = item.badge,
                    fontSize = 14.sp,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}
