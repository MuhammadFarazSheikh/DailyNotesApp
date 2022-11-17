package com.composesample.dailynotesapp.composecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.composesample.dailynotesapp.activities.Constants.Companion.dailyNotesList
import com.composesample.dailynotesapp.utils.getUserDailyNotes
import com.composesample.dailynotesapp.utils.showLoaderAlert
import kotlinx.coroutines.CoroutineScope
import com.composesample.dailynotesapp.R
import com.composesample.dailynotesapp.activities.Constants.Companion.userDailyNotesListLiveData

@Composable
fun dailyNotesListScreen(coroutineScope: CoroutineScope)
{
    val isShowLoader = remember{ mutableStateOf(true) }
    if(isShowLoader.value)
    {
        showLoaderAlert(stringResource(R.string.text_loading_user_daily_notes))
        getUserDailyNotes(coroutineScope,isShowLoader)
    }

    userDailyNotesListLiveData.observeAsState().let { state->
        state?.value?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it)
                    { index,item->
                        Row(
                            content = {
                                Text(
                                    text = item,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                )

                                IconButton(
                                    onClick = {

                                    },
                                    content = {
                                        Icon(
                                            painter = painterResource(R.drawable.delete_icon),
                                            contentDescription =""
                                        )
                                    }
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(7.dp, 10.dp, 7.dp, 0.dp)
                                .background(
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(5.dp)
                                ),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
            )
        }
    }
}