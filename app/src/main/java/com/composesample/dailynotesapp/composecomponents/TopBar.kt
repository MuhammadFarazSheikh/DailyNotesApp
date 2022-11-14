package com.composesample.dailynotesapp.composecomponents

import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun setupTopBar(topBarTitle:String,coroutineScope: CoroutineScope,scaffoldState: ScaffoldState)
{
    TopAppBar(
        backgroundColor = Color.LightGray,
        title = {
            Text(
                topBarTitle,
                fontSize = 17.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                content = {
                    Image(
                        Icons.Default.Menu,
                        contentDescription ="",
                        colorFilter = ColorFilter.tint(color = Color.White)
                    )
                }
            )
        }
    )
}