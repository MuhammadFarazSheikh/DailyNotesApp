package com.composesample.dailynotesapp.composecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.composesample.dailynotesapp.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun setupLoginScreen()
{
    val textFieldEmailState = remember{ mutableStateOf(String()) }
    val textFieldPasswordState = remember{ mutableStateOf(String()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
            .padding(15.dp, 40.dp, 15.dp, 0.dp),
        content = {
            TextField(
                value = textFieldEmailState.value ,
                onValueChange = { value->
                    textFieldEmailState.value = value
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.LightGray, RoundedCornerShape(5.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    textColor = Color.White,
                    cursorColor = Color.Black
                ),
                placeholder = {
                    Text(
                        stringResource(R.string.hint_email),
                        fontSize = 15.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            TextField(
                value = textFieldPasswordState.value ,
                onValueChange = { value->
                    textFieldPasswordState.value = value
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp, 13.dp, 0.dp, 0.dp)
                    .background(color = Color.LightGray, RoundedCornerShape(5.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    textColor = Color.White,
                    cursorColor = Color.Black
                ),
                placeholder = {
                    Text(
                        stringResource(R.string.hint_password),
                        fontSize = 15.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            TextButton(
                onClick = { },
                content = {
                          Text(
                              stringResource(R.string.text_login),
                              fontWeight = FontWeight.SemiBold,
                              color = Color.White,
                              fontSize = 15.sp
                          )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp, 18.dp, 0.dp, 0.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(25.dp)
                    )
            )
        },
    )
}
