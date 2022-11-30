package com.composesample.dailynotesapp.presentation.composablescreens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composesample.dailynotesapp.utils.getUserDailyNotes
import com.composesample.dailynotesapp.utils.showLoaderAlert
import kotlinx.coroutines.CoroutineScope
import com.composesample.dailynotesapp.R
import com.composesample.dailynotesapp.activities.Constants.Companion.userDailyNotesListLiveData

@Composable
fun dailyNotesListScreen(coroutineScope: CoroutineScope,isShowActionsIcons: MutableState<Boolean>)
{
    val isShowLoaderDeleteDailyNotes = remember{ mutableStateOf(false) }
    val isShowLoaderDailyNotesList = remember{ mutableStateOf(true) }
    if(isShowLoaderDailyNotesList.value)
    {
        showLoaderAlert(stringResource(R.string.text_loading_user_daily_notes))
        getUserDailyNotes(coroutineScope,isShowLoaderDailyNotesList)
    }

    if(isShowLoaderDeleteDailyNotes.value)
    {
        showLoaderAlert(stringResource(R.string.text_delete_note))
    }

    userDailyNotesListLiveData.observeAsState().let { state->
        state?.value?.let {
            if(it.size>0) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    content = {
                              itemsIndexed(it)
                              {
                                  index, item ->
                                  Text(
                                      text = item,
                                      fontSize = 16.sp,
                                      color = Color.White,
                                      modifier = Modifier
                                          .fillMaxWidth()
                                          .defaultMinSize(minHeight = 100.dp)
                                          .padding(7.dp, 10.dp, 10.dp, 0.dp)
                                          .background(
                                              color = Color.LightGray,
                                              shape = RoundedCornerShape(5.dp)
                                          )
                                          .padding(10.dp, 0.dp)
                                          .pointerInput(Unit) {
                                              detectTapGestures (
                                                  onLongPress = {
                                                      isShowActionsIcons.value = true
                                                  }
                                              )
                                          },
                                      textAlign = TextAlign.Start
                                  )
                              }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(0.dp, 0.dp, 0.dp, 2.dp)
                )
            }
            else
            {
                setupNoDataView()
            }
        }
    }
}