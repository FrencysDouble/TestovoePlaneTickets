package com.example.testovoeplanetickets.main_ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.testovoeplanetickets.R
import com.example.testovoeplanetickets.controllers.CalendarController
import com.example.testovoeplanetickets.controllers.SearchScreenController
import com.example.testovoeplanetickets.controllers.SelectedText
import com.example.testovoeplanetickets.main_ui.navigation.BottomNavigationBar
import com.example.testovoeplanetickets.main_ui.navigation.Routes
import com.example.testovoeplanetickets.models.SearchScreenModel
import com.example.testovoeplanetickets.ui.theme.BasicBlack
import com.example.testovoeplanetickets.ui.theme.BasicGrey
import com.example.testovoeplanetickets.ui.theme.BasicGrey2
import com.example.testovoeplanetickets.ui.theme.BasicGrey3
import com.example.testovoeplanetickets.ui.theme.BasicGrey4
import com.example.testovoeplanetickets.ui.theme.BasicGrey5
import com.example.testovoeplanetickets.ui.theme.SpecialBlue
import com.example.testovoeplanetickets.ui.theme.SpecialDarkBlue


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavHostController,
    selectedText: SelectedText,
    calendarController: CalendarController,
    controller: SearchScreenController
)
{
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController = navController) }) {
        ScreenS(navController, selectedText,calendarController,controller)
    }

    LaunchedEffect(Unit)
    {
        controller.dataLoad()
    }

}

@Composable
private fun ScreenS(
    navController: NavHostController,
    selectedText: SelectedText,
    calendarController: CalendarController,
    controller: SearchScreenController
)
{
    val liveSearchData = controller.liveSearchData.observeAsState()
    Column(
        Modifier
            .fillMaxSize()
            .background(BasicBlack)
            .padding(start = 12.dp, end = 12.dp, top = 48.dp)) {
        UpperBar(selectedText,calendarController,controller)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp))
        liveSearchData.value?.let { MiddleBar(it) }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp))
        EndBtns(navController)

    }

}


@Composable
private fun UpperBar(
    selectedText: SelectedText,
    calendarController: CalendarController,
    controller: SearchScreenController
)
{
    Column {
        SearchBar(selectedText)
        LazyRow(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)
        )
        {
            item {
                BtnListItemWithIcon(iconId = R.drawable.icon_adder, text = "обратно", onClick = {calendarController.openCalendar()})
            }
            item {
                BtnListItem(onClick = {calendarController.openCalendar()},controller)
            }
            item {
                BtnListItemWithIcon(
                    iconId = R.drawable.search_screen_profile_icon,
                    text = "1,эконом",
                    onClick = {}
                )
            }
            item {
                BtnListItemWithIcon(
                    iconId = R.drawable.search_screen_settings_icon,
                    text = "фильтры",
                    onClick = {}
                )
            }
        }
    }

}


@Composable
private fun SearchBar(selectedText: SelectedText)
{
    val selectedDestination by selectedText.selectedDestination.observeAsState("")
    val selectedApproach by selectedText.selectedApproach.observeAsState("")
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(BasicGrey2))
    {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.search_bar_reset_icon), contentDescription ="",tint = Color.White)
            }
            Column(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                   Text(text = selectedDestination, fontSize = 16.sp,color = Color.White)
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd)
                    {
                        IconButton(onClick = { selectedText.shuffleText() }) {
                            Icon(painter = painterResource(id = R.drawable.search_bar_shuffle_icon), contentDescription = "",tint = Color.White)

                            
                        }
                    }
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BasicGrey3))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = selectedApproach,fontSize = 16.sp,color = Color.White)
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd)
                    {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(painter = painterResource(id = R.drawable.dialog_icon_delete), contentDescription = "",tint = Color.White)
                            
                        }
                    }

                }

            }
        }
    }

}

@Composable
private fun BtnListItemWithIcon(
    iconId : Int,
    text : String,
    onClick: () -> Unit)
{
    Box(modifier = Modifier
        .wrapContentWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(BasicGrey2))
    {
        Row(
            Modifier
                .padding(6.dp)
                .clickable {
                    onClick()
                }, verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = iconId), contentDescription ="",Modifier.padding(end = 8.dp),tint = Color.White )
            Text(text = text, fontSize = 14.sp,color = Color.White, fontStyle = FontStyle.Italic)
        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun BtnListItem(onClick: () -> Unit, controller: SearchScreenController)
{
    Box(modifier = Modifier
        .wrapContentWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(BasicGrey2))
    {
        Row(
            Modifier
                .padding(6.dp)
                .clickable {
                    onClick()
                }) {
            Text(text = controller.getCurrentDate(), fontSize = 14.sp,color = Color.White, fontStyle = FontStyle.Italic)
            Text(text = " сб", fontSize = 14.sp,color = BasicGrey5, fontStyle = FontStyle.Italic)
        }

    }

}

@Composable
private fun MiddleBar(liveSearchData : List<SearchScreenModel>)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(BasicGrey))
    {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(18.dp)) {
            Text(text = stringResource(id = R.string.search_screen_list_title), fontSize = 20.sp,color= Color.White)
            LazyColumn(Modifier.padding(top = 12.dp),verticalArrangement = Arrangement.spacedBy(12.dp))
            {
                items(liveSearchData){ item ->
                    PopularListItem(item)
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp), contentAlignment = Alignment.Center)
            {
                Text(
                    text = stringResource(id = R.string.search_screen_show_all),
                    color = SpecialBlue,
                    fontSize = 16.sp
                )
            }
        }
    }

}

@Composable
private fun PopularListItem(item: SearchScreenModel) {
    Column {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp))
        {
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = ""
            )
            Column(Modifier.padding(start = 6.dp)) {
                Row {
                    Text(text = item.title, fontSize = 14.sp, color = Color.White, fontStyle = FontStyle.Italic)
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd)
                    {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "${item.price} ₽", fontSize = 14.sp, color = SpecialBlue, fontStyle = FontStyle.Italic)
                            Icon(
                                painter = painterResource(id = R.drawable.search_screen_right_stroke),
                                contentDescription = "",
                                Modifier.padding(start = 6.dp),
                                tint = SpecialBlue
                            )
                        }
                    }
                }
                LazyRow(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)
                )
                {
                    items(item.time_range) { time ->
                        Text(text = time, fontSize = 14.sp,color = Color.White)
                    }
                }
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .background(BasicGrey4)
            .height(1.dp))
    }

}


@Composable
private fun EndBtns(navController: NavHostController)
{
    var isSwitched by remember { mutableStateOf(false) }
    Column {
        Button(onClick = { navController.navigate(Routes.Screens.SearchScreenResult.route) },shape = RoundedCornerShape(12.dp),colors = ButtonColors(
            containerColor = SpecialBlue,
            contentColor = Color.White,
            disabledContainerColor = SpecialBlue,
            disabledContentColor = Color.White
        )) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp), contentAlignment = Alignment.Center)
            {
                Text(
                    text = stringResource(id = R.string.search_screen_btn1),
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(BasicGrey))
        {
            Row(Modifier.padding(12.dp),verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.subscribe_icon), contentDescription ="",Modifier.padding(start = 12.dp,end = 8.dp) )
                Text(text = stringResource(id = R.string.search_screen_subscribe_pice), fontSize = 16.sp,color = Color.White)
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd)
                {
                    CustomSwitch(
                        checked = isSwitched,
                        onCheckedChange = { isSwitched = it }
                    )
                }
            }
        }
    }

}

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    checkedThumbColor: Color = SpecialBlue,
    uncheckedThumbColor: Color = Color.White,
    checkedTrackColor: Color = SpecialDarkBlue,
    uncheckedTrackColor: Color = Color.DarkGray,
    thumbShape: Shape = CircleShape,
    trackShape: Shape = RoundedCornerShape(10.dp)
) {
    val thumbOffset = if (checked) 20.dp else 0.dp
    val trackColor = if (checked) checkedTrackColor else uncheckedTrackColor
    val thumbColor = if (checked) checkedThumbColor else uncheckedThumbColor

    Box(
        modifier = modifier
            .width(40.dp)
            .height(30.dp)
        , contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(15.dp)
                .width(36.dp)
                .clip(trackShape)
                .background(trackColor)
        )
        Box(
            modifier = Modifier
                .size(20.dp)
                .offset(x = thumbOffset)
                .clip(thumbShape)
                .background(thumbColor)
                .clickable { onCheckedChange(!checked) }
                .align(Alignment.CenterStart)
        )
    }
}
