package com.composesample.dailynotesapp.composecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composesample.dailynotesapp.activities.Constants.Companion.dailyNotesList

@Composable
fun dailyNotesListScreen()
{
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content ={
            itemsIndexed(dailyNotesList)
            { index,item->
                Text(
                    text = item,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(7.dp)
                )
            }
        },
        contentPadding = PaddingValues(7.dp)
    )
}