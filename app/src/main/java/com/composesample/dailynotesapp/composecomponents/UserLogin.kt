package com.composesample.dailynotesapp.composecomponents

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composesample.dailynotesapp.AppClass.Companion.appContext
import com.composesample.dailynotesapp.activities.DashboardScreen
import com.composesample.dailynotesapp.activities.utils.PreferenceDataStore
import com.composesample.dailynotesapp.models.UserData
import com.composesample.dailynotesapp.utils.Keys.Companion.FIREBASE_USERS_COLLECTION
import com.composesample.dailynotesapp.utils.showLoaderAlert
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun setupLoginScreen(coroutineScope: CoroutineScope)
{
    val isShowLoaderDialoge = remember{ mutableStateOf(false) }
    val isFirebaseLogin = remember{ mutableStateOf(false) }
    val textFieldEmailState = remember{ mutableStateOf(String()) }
    val textFieldPasswordState = remember{ mutableStateOf(String()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
            .padding(15.dp, 40.dp, 15.dp, 0.dp),
        content = {

            Image(
                painter = painterResource(R.drawable.login_icon),
                contentDescription ="",
                colorFilter = ColorFilter.tint(color = Color.LightGray),
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally)
            )

            TextField(
                value = textFieldEmailState.value ,
                onValueChange = { value->
                    textFieldEmailState.value = value
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp, 50.dp, 0.dp, 0.dp)
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
                onClick = {
                    if(textFieldEmailState.value.isNullOrBlank() || textFieldEmailState.value.isNullOrEmpty())
                    {
                        Toast.makeText(appContext, appContext.getString(R.string.text_email_empty),Toast.LENGTH_LONG).show()
                    }
                    else if(textFieldPasswordState.value.isNullOrBlank() || textFieldPasswordState.value.isNullOrEmpty())
                    {
                        Toast.makeText(appContext, appContext.getString(R.string.text_password_empty),Toast.LENGTH_LONG).show()
                    }
                    else {
                        isFirebaseLogin.value = true
                    }
                },
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

    if(isFirebaseLogin.value)
    {
        isShowLoaderDialoge.value = true
        Firebase
            .firestore
            .collection(FIREBASE_USERS_COLLECTION)
            .document(textFieldEmailState.value+"-"+textFieldPasswordState.value)
            .get()
            .addOnCompleteListener {
                isFirebaseLogin.value = false
            }.addOnCanceledListener {
                isFirebaseLogin.value = false
            }.addOnFailureListener {
                isFirebaseLogin.value = false
            }.addOnSuccessListener { documentSnapshot->
                isShowLoaderDialoge.value = false
                isFirebaseLogin.value = false

                if(documentSnapshot.exists())
                {
                    coroutineScope.launch {
                        PreferenceDataStore.setIsUserLoggedIn(true,appContext)
                        PreferenceDataStore.saveUserData(
                            Gson().toJson(documentSnapshot.data),
                            appContext
                        )
                        appContext.startActivity(Intent(appContext, DashboardScreen::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                    }
                }
            }
    }

    if(isShowLoaderDialoge.value)
    {
        showLoaderAlert(
            stringResource(R.string.text_user_login)
        )
    }
}
