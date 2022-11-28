package com.composesample.dailynotesapp.presentation.composablescreens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.composesample.dailynotesapp.R

@Composable
fun setupNoDataView()
{
    Column(
        content = {
            Text(
                stringResource(R.string.text_no_data),
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                fontSize = 15.sp,
                modifier = Modifier.wrapContentHeight().wrapContentWidth()
            )
        },
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}