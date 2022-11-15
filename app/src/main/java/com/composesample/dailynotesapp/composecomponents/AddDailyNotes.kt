package com.composesample.dailynotesapp.composecomponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.composesample.dailynotesapp.R
import com.composesample.dailynotesapp.activities.Constants.Companion.DAILY_NOTES_LIST_ROUTE

@Composable
fun addToDailyNotes(navHostController: NavHostController)
{
    val isAddNoteToFirebase = remember{ mutableStateOf(false) }
    val isShowLoaderAlert = remember{ mutableStateOf(false) }
    val noteTextField = remember{ mutableStateOf(String()) }
    Column(
        content = {
            TextField(
                value =noteTextField.value,
                onValueChange ={ value ->
                    noteTextField.value = value
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = Color.White,
                    cursorColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(5.dp),
                placeholder = {
                    Text(
                        stringResource(R.string.hint_add_note),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                },
            )

            TextButton(
                onClick = {
                    navHostController.navigate(DAILY_NOTES_LIST_ROUTE)
                    {
                        launchSingleTop = true
                    }
                },
                content = {
                    Text(
                        stringResource(R.string.text_add_to_notes),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp,15.dp,0.dp,0.dp)
            )
        },
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(20.dp),
    )
}