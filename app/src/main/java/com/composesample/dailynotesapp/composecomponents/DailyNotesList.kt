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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import com.composesample.dailynotesapp.activities.Constants.Companion.dailyNotesList
import com.composesample.dailynotesapp.utils.getUserDailyNotes
import com.composesample.dailynotesapp.utils.showLoaderAlert
import kotlinx.coroutines.CoroutineScope
import com.composesample.dailynotesapp.R
import com.composesample.dailynotesapp.activities.Constants.Companion.userDailyNotesListLiveData
import com.composesample.dailynotesapp.utils.deleteDailyNote

@Composable
fun dailyNotesListScreen(coroutineScope: CoroutineScope)
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
                LazyColumn(
                    content = {
                        itemsIndexed(it)
                        { index, item ->
                            ConstraintLayout(
                                content = {
                                    val (textDailyNoteConstraint, deleteIconConstraint) = createRefs()
                                    Text(
                                        text = item,
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp, 0.dp)
                                            .constrainAs(textDailyNoteConstraint)
                                            {
                                                top.linkTo(parent.top, 11.dp)
                                                bottom.linkTo(parent.bottom, 7.dp)
                                            },
                                        textAlign = TextAlign.Start
                                    )

                                    IconButton(
                                        onClick = {
                                            isShowLoaderDeleteDailyNotes.value = true
                                            deleteDailyNote(
                                                coroutineScope, isShowLoaderDeleteDailyNotes, index
                                            )
                                        },
                                        content = {
                                            Icon(
                                                painter = painterResource(R.drawable.delete_icon),
                                                contentDescription = ""
                                            )
                                        },
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .wrapContentHeight()
                                            .constrainAs(deleteIconConstraint)
                                            {
                                                top.linkTo(parent.top, -20.dp)
                                                end.linkTo(parent.end, -20.dp)
                                            }
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(7.dp, 10.dp, 10.dp, 0.dp)
                                    .background(
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(5.dp)
                                    ),
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