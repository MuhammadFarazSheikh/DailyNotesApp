package com.composesample.dailynotesapp.presentation.composablescreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
                                    val (
                                        textDailyNoteConstraint,
                                        deleteButtonConstraint,
                                        updateButtonConstraint
                                    ) = createRefs()
                                    Text(
                                        text = item,
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .defaultMinSize(minHeight = 100.dp)
                                            .padding(10.dp, 0.dp)
                                            .constrainAs(textDailyNoteConstraint)
                                            {
                                                top.linkTo(parent.top, 5.dp)
                                            },
                                        textAlign = TextAlign.Start
                                    )

                                    TextButton(
                                        onClick = {

                                        },
                                        content = {
                                            Text(
                                                stringResource(R.string.text_delete_button),
                                                color = Color.White,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        },
                                        shape = RoundedCornerShape(25.dp),
                                        modifier = Modifier.constrainAs(deleteButtonConstraint) {
                                            top.linkTo(textDailyNoteConstraint.bottom,7.dp)
                                            start.linkTo(parent.start)
                                            end.linkTo(updateButtonConstraint.start)
                                        }.width(0.dp)
                                            .wrapContentHeight()
                                            .background(color = Color.DarkGray)
                                            )

                                    TextButton(
                                        onClick = {

                                        },
                                        content = {
                                            Text(
                                                stringResource(R.string.text_update_button),
                                                color = Color.White,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        },
                                        shape = RoundedCornerShape(25.dp),
                                        modifier = Modifier.constrainAs(deleteButtonConstraint) {
                                            top.linkTo(textDailyNoteConstraint.bottom,7.dp)
                                            start.linkTo(deleteButtonConstraint.end)
                                            end.linkTo(parent.end)
                                        }.width(0.dp)
                                            .wrapContentHeight()
                                            .background(color = Color.DarkGray)
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