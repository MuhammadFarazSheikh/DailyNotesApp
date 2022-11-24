package com.composesample.dailynotesapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.composesample.dailynotesapp.R

@Composable
fun showMessageAlertWithOkAndCancelButton(title:String,messageText:String,okButton:()->Unit,cancelButton:()->Unit)
{
    AlertDialog(
        onDismissRequest = {},
        title = {
                Text(
                    title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
        },
        text = {
            Text(
                messageText,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
        },
        confirmButton = {
                        TextButton(
                            onClick = {
                                okButton.invoke()
                            },
                            content = {
                                Text(
                                    stringResource(R.string.text_ok_button),
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp
                                )
                            }
                        )
        },
        backgroundColor = Color.White,
        shape = RoundedCornerShape(7.dp),
        dismissButton = {
            TextButton(
                onClick = {
                    cancelButton.invoke()
                },
                content = {
                    Text(
                        stringResource(R.string.text_cancel_button),
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
            )
        }
    )
}

@Composable
fun showLoaderAlert(loaderText:String)
{
    Dialog(
        onDismissRequest = {},
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.White, shape = RoundedCornerShape(7.dp))
                    .padding(0.dp, 15.dp, 0.dp, 15.dp),
                content = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp),
                        color = Color.DarkGray,
                        strokeWidth = 2.dp
                    )

                    Text(
                        loaderText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray,
                        modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(0.dp,10.dp,0.dp,0.dp)
                    )
                },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
        },
    )
}