package com.composesample.dailynotesapp.composecomponents

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.composesample.dailynotesapp.activities.Constants.Companion.dailyNotesList

@Composable
fun dailyNotesListScreen()
{
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content ={
            itemsIndexed(dailyNotesList)
            { index,item->
                Text(text = item)
            }
        }
    )
}