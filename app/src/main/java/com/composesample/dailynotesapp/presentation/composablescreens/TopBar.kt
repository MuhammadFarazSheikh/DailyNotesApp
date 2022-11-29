package com.composesample.dailynotesapp.presentation.composablescreens

import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.composesample.dailynotesapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun setupTopBar(
    topBarTitle:String,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    isShowActionsIcons: MutableState<Boolean>
)
{
    TopAppBar(
        backgroundColor = Color.LightGray,
        title = {
            Text(
                topBarTitle,
                fontSize = 17.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            if(!isShowActionsIcons.value) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    content = {
                        Image(
                            Icons.Default.Menu,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(color = Color.Black)
                        )
                    }
                )
            }
            else {
                IconButton(
                    onClick = {
                        isShowActionsIcons.value = false
                    },
                    content = {
                        Image(
                            Icons.Default.ArrowBack,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(color = Color.Black)
                        )
                    }
                )
            }
        },
        actions = {
            if(isShowActionsIcons.value) {
                IconButton(
                    onClick = {

                    },
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.delete),
                            contentDescription = "",
                        )
                    }
                )

                IconButton(
                    onClick = {

                    },
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.edit_text),
                            contentDescription = "",
                        )
                    }
                )
            }
        }
    )
}