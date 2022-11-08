package com.composesample.dailynotesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.composesample.dailynotesapp.R
import com.composesample.dailynotesapp.activities.utils.PreferenceDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    private lateinit var coroutineScope:CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    splashScreenUI()
                }
            }
        )
        startTimer()
    }

    /*START 2 SECONDS TIMER FOR SPLASH SCREEN*/
    private fun startTimer()
    {
        object:CountDownTimer(2000,1000)
        {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                checkUserSession()
            }
        }.start()
    }

    /*CHECK USER SESSION IF ALREADY LOGIN SO OPEN MAIN SCREEN*/
    /*OTHERWISE OPEN LOGIN OR SIGNUP SCREEN*/
    private fun checkUserSession()
    {
        coroutineScope.launch {
            PreferenceDataStore.checkIsUserLoggedIn(this@SplashScreen).collectLatest { isUserLoggedIn->
                when(isUserLoggedIn)
                {
                    true ->{

                    }
                    false ->
                    {
                        startActivity(Intent(this@SplashScreen,LoginSignUpScreen::class.java))
                    }
                }
            }
        }
    }

    @Composable
    fun splashScreenUI()
    {
        coroutineScope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White),
            content ={
                Text(
                    stringResource(R.string.text_splash_title),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
    }
}