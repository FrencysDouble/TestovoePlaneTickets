package com.example.testovoeplanetickets.main_ui

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.testovoeplanetickets.R
import com.example.testovoeplanetickets.controllers.PlaneScreenController
import com.example.testovoeplanetickets.controllers.SelectedText
import com.example.testovoeplanetickets.main_ui.navigation.BottomNavigationBar
import com.example.testovoeplanetickets.models.PlaneScreenModel
import com.example.testovoeplanetickets.ui.theme.BasicBlack
import com.example.testovoeplanetickets.ui.theme.BasicGrey2
import com.example.testovoeplanetickets.ui.theme.BasicGrey3
import com.example.testovoeplanetickets.ui.theme.BasicGrey4
import com.example.testovoeplanetickets.ui.theme.BasicGrey5


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlaneScreen(
    navController: NavHostController,
    controller: PlaneScreenController,
    selectedText: SelectedText
)
{
    val isDialogShow by remember { derivedStateOf { controller.isDialogShow } }
    val planeDataList = controller.livePlaneData.observeAsState()

    DisposableEffect(Unit) {
        onDispose { controller.dialogDisable() }
    }

    LaunchedEffect(Unit)
    {
        controller.dataLoad()
    }

    if (isDialogShow.value)
    {
        BottomSheetDialog(onDismissRequest = {controller.dialogDisable()} ,navController,controller,selectedText)
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController = navController) }) {
        Screen(navController,controller,selectedText,planeDataList.value)
    }
}

@Composable
private fun Screen(
    navController: NavHostController,
    controller: PlaneScreenController,
    selectedText: SelectedText,
    planeDataList: List<PlaneScreenModel>?
)
{
    Column(
        Modifier
            .fillMaxSize()
            .background(BasicBlack)
            .padding(start = 24.dp, end = 24.dp, top = 24.dp)) {
        TextTitle()
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp))
        SearchBar(navController,controller,selectedText)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp))
        planeDataList?.let { MusicList(it) }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp))
        bottomButton()

    }

}


@Composable
private fun TextTitle()
{
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            text = stringResource(id = R.string.plane_screen_title_part_1),
            fontSize = 22.sp,
            color = Color.White
        )
        Text(
            text = stringResource(id = R.string.plane_screen_title_part_2),
            fontSize = 22.sp,
            color = Color.White
        )

    }


}

@Composable
private fun SearchBar(navController: NavHostController,controller: PlaneScreenController,selectedText: SelectedText)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(BasicGrey2)
    )
    {
        Box(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(BasicGrey3)
        )
        {
            Row(
                Modifier
                    .wrapContentSize()
                    .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.search_icon_bar), contentDescription ="" )
                Column(
                    Modifier
                        .padding(start = 12.dp)
                        .wrapContentSize()) {
                    TextField(BasicGrey3,R.string.plane_screen_search_1,Color.White,controller,selectedText)
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .background(BasicGrey4)
                        .height(1.dp))
                    Text(text = stringResource(id = R.string.plane_screen_search_2),
                        Modifier
                            .padding(top = 8.dp)
                            .clickable {
                                controller.dialogActive()

                            },color = BasicGrey5)
                }
            }
        }
    }
}

@Composable
private fun MusicList(planeDataList: List<PlaneScreenModel>)
{
    Column(Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.plane_screen_list_title), fontSize = 22.sp,color = Color.White)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(60.dp))
        {
            items(planeDataList){item ->
                MusicItemList(item)
            }
        }
    }

}

@Composable
private fun MusicItemList(item: PlaneScreenModel)
{
    val color = Color.White
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Image(painter = painterResource(id =item.imageId),
            contentDescription ="",
            Modifier
                .width(132.dp)
                .height(132.dp))
        Text(text = item.title,color = color, fontSize = 16.sp)
        Text(text = item.town,color = color, fontSize = 14.sp)
        Row {
            Icon(painter = painterResource(id = R.drawable.plane_icon_list), contentDescription ="",tint = BasicGrey5)
            Text(text = "от ${item.price} ₽",color = color, fontSize = 14.sp)

        }
    }

}

@Composable
@Preview
private fun bottomButton()
{
    Button(onClick = { /*TODO*/ },Modifier.fillMaxWidth(),
        colors = ButtonColors
            (
            contentColor = Color.White,
            containerColor = BasicGrey2,
            disabledContentColor = Color.White,
            disabledContainerColor = BasicGrey2)) {
        Text(text = stringResource(id = R.string.plane_screen_btn_txt), fontStyle = FontStyle.Italic)
        
    }

}

@Composable
private fun TextField(
    backgroundColor: Color,
    hint: Int,
    textColor: Color,
    controller: PlaneScreenController,
    selectedText: SelectedText
)
{
    Log.d("dsad", selectedText.selectedDestination.value.toString())

    var text by remember {
        mutableStateOf(
            selectedText.selectedDestination.value?.toString() ?: ""
        )
    }




    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(bottom = 12.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done

        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                controller.saveCacheData(text)
                selectedText.setDestination(controller.getCacheData())
            },
        ),

        decorationBox = { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    text = stringResource(id = hint),
                    color = textColor,
                    fontSize = 16.sp
                )
            }
            innerTextField()
        },
        textStyle = TextStyle(color = textColor)
    )


}
