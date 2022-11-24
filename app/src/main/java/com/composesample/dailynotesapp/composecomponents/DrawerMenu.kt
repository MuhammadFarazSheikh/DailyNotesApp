package com.composesample.dailynotesapp.composecomponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composesample.dailynotesapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun setupDrawerMenu(scaffoldState: ScaffoldState,coroutineScope: CoroutineScope)
{
    Column(
        content = {
                  Text(
                      stringResource(R.string.text_daily_notes_app),
                      fontSize = 22.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color.DarkGray,
                      textAlign = TextAlign.Center,
                      modifier = Modifier
                          .fillMaxWidth()
                          .wrapContentHeight()
                          .padding(0.dp, 25.dp, 0.dp, 0.dp)
                  )

            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp, 25.dp, 0.dp, 0.dp)
            )

            ClickableText(
                text = AnnotatedString(stringResource(R.string.text_settings)),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                    fontSize = 15.sp
                ),
                onClick ={

                },
                modifier = Modifier.wrapContentHeight().wrapContentWidth().padding(10.dp,20.dp,0.dp,0.dp)
            )

            ClickableText(
                text = AnnotatedString(stringResource(R.string.text_share_app)),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                    fontSize = 15.sp
                ),
                onClick ={

                },
                modifier = Modifier.wrapContentHeight().wrapContentWidth().padding(10.dp,10.dp,0.dp,0.dp)
            )

            ClickableText(
                text = AnnotatedString(stringResource(R.string.text_rate_app)),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                    fontSize = 15.sp
                ),
                onClick ={

                },
                modifier = Modifier.wrapContentHeight().wrapContentWidth().padding(10.dp,10.dp,0.dp,0.dp)
            )

            ClickableText(
                text = AnnotatedString(stringResource(R.string.text_logout)),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                    fontSize = 15.sp
                ),
                onClick ={
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                },
                modifier = Modifier.wrapContentHeight().wrapContentWidth().padding(10.dp,10.dp,0.dp,0.dp)
            )
        },
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    )
}