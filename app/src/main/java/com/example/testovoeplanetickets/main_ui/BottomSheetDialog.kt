package com.example.testovoeplanetickets.main_ui

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.testovoeplanetickets.R
import com.example.testovoeplanetickets.controllers.PlaneScreenController
import com.example.testovoeplanetickets.controllers.SelectedText
import com.example.testovoeplanetickets.main_ui.navigation.Routes
import com.example.testovoeplanetickets.ui.theme.BasicBlack
import com.example.testovoeplanetickets.ui.theme.BasicGrey2
import com.example.testovoeplanetickets.ui.theme.BasicGrey3
import com.example.testovoeplanetickets.ui.theme.BasicGrey4
import com.example.testovoeplanetickets.ui.theme.BasicGrey5
import com.example.testovoeplanetickets.ui.theme.DialogColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDialog(
    onDismissRequest: () -> Unit,
    navController: NavHostController,
    controller: PlaneScreenController,
    selectedText: SelectedText
)
{
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true
    )
    ModalBottomSheet(onDismissRequest = { onDismissRequest.invoke()},
        sheetState = bottomSheetState,
        containerColor = BasicBlack,
        modifier = Modifier.fillMaxSize()) {

        Dialog(navController,controller,selectedText)
    }

}


@Composable
private fun Dialog(
    navController: NavHostController,
    controller: PlaneScreenController,
    selectedText: SelectedText
)
{
    Column(
        Modifier
            .fillMaxSize()
            .background(DialogColor)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 24.dp)) {
            UpperSearchScreen(navController,controller,selectedText)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp))
            MiddleBar()
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp))
            EndDialogList(selectedText,navController)
        }

    }

}


@Composable
private fun UpperSearchScreen(
    navController: NavHostController,
    controller: PlaneScreenController,
    selectedText: SelectedText
)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(BasicGrey2))
    {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(12.dp), verticalArrangement = Arrangement.Center) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp, top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Icon(painter = painterResource(id = R.drawable.bottom_dialog_plane), contentDescription ="" ,tint = BasicGrey4)
                Text(text = selectedText.selectedDestination.value?: "", fontSize = 16.sp,color = Color.White)
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .background(BasicGrey3)
                .height(1.dp))
            Row(
                Modifier
                    .wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Icon(painter = painterResource(id = R.drawable.bottom_dialog_search_icon), contentDescription = "",tint = Color.White)
                DialogTextField(
                    backgroundColor = BasicGrey2,
                    textColor = BasicGrey5 ,
                    hint = R.string.plane_screen_search_2,
                    navController = navController,
                    selectedText = selectedText
                )

            }

        }
    }

}

@Composable
fun DialogTextField(backgroundColor: Color, hint: Int, textColor: Color,navController: NavHostController,selectedText: SelectedText) {
    val focusManager = LocalFocusManager.current
    val selectedApproach by selectedText.selectedApproach.observeAsState("")
    var text by remember { mutableStateOf(selectedApproach) }

    LaunchedEffect(selectedApproach) {
        text = selectedApproach ?: ""
    }
    BasicTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 8.dp, horizontal = 2.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done

        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                if(!text.isBlank()) {
                    selectedText.setApproach(text)
                    navController.navigate(Routes.Screens.SearchScreen.route)
                }
            },
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor, shape = RoundedCornerShape(12.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = stringResource(id = hint),
                            color = textColor,
                            fontSize = 16.sp,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                    innerTextField()
                }
                    IconButton(onClick = { text = "" },
                        Modifier
                            .width(24.dp)
                            .height(24.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.dialog_icon_delete),
                            contentDescription = "Clear text",
                            tint = textColor
                        )
                    }
            }
        },
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp)
    )
}


@Composable
private fun MiddleBar()
{
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        MiddleBarItem(iconId = R.drawable.dialog_bar_icon1, textId = R.string.dialog_bar_icon_txt_1,2)
        MiddleBarItem(iconId = R.drawable.dialog_bar_icon2, textId = R.string.dialog_bar_icon_txt_2,2)
        MiddleBarItem(iconId = R.drawable.dialog_bar_icon3, textId = R.string.dialog_bar_icon_txt_3,1)
        MiddleBarItem(iconId = R.drawable.dialog_bar_icon4, textId = R.string.dialog_bar_icon_txt_4,2)
    }
}


@Composable
private fun MiddleBarItem(iconId : Int,textId : Int,linesAmount : Int)
{
    Column(
        Modifier
            .width(80.dp)
            .wrapContentHeight(), verticalArrangement = Arrangement.spacedBy(2.dp)) {
        IconButton(onClick = { /*TODO*/ },Modifier.width(80.dp)) {
            Image(painter = painterResource(id = iconId), contentDescription ="" )
        }
        Box(modifier = Modifier
            .wrapContentWidth()
            .fillMaxWidth(), contentAlignment = Alignment.Center)
        {
            Text(text = stringResource(id = textId), fontSize = 14.sp, color = Color.White, textAlign = TextAlign.Center, maxLines = linesAmount)
        }
    }
}



@Composable
private fun EndDialogList(selectedText: SelectedText, navController: NavHostController)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(BasicGrey2)
    )
    {
        LazyColumn(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp))
        {
            item {
                EndDialogListItem(R.drawable.dialog_bar_photo1,"Стамбул",selectedText,navController)
            }
            item {
                EndDialogListItem(R.drawable.dialog_bar_photo2, "Сочи", selectedText, navController)
            }
            item {
                EndDialogListItem(
                    R.drawable.dialog_bar_photo3,
                    "Пхукет",
                    selectedText,
                    navController
                )
            }
        }
    }

}
@Composable
private fun EndDialogListItem(
    imageId: Int,
    text: String,
    selectedText: SelectedText,
    navController: NavHostController
)
{
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 6.dp))
    {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .clickable
                {
                    selectedText.setApproach(text)
                    navController.navigate(Routes.Screens.SearchScreen.route)
                }) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "",
                Modifier
                    .width(40.dp)
                    .height(40.dp)
            )
            Column(Modifier.padding(start = 12.dp)) {
                Text(text = text, fontSize = 16.sp, color = Color.White)
                Text(
                    text = stringResource(id = R.string.dialog_bar_list_endtitle),
                    fontSize = 14.sp,
                    color = BasicGrey4
                )
            }

        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(BasicGrey4))
    }

}