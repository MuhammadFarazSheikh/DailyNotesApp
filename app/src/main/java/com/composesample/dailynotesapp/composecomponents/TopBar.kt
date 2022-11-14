package com.composesample.dailynotesapp.composecomponents

import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun setupTopBar(topBarTitle:String)
{
    TopAppBar(
        backgroundColor = Color.LightGray,
        title = {
            Text(
                topBarTitle,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {

                },
                content = {
                    Image(
                        Icons.Default.Menu,
                        contentDescription =""
                    )
                }
            )
        }
    )
}