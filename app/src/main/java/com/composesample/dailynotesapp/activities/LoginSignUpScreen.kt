package com.composesample.dailynotesapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composesample.dailynotesapp.composecomponents.setupBottomNavigation
import com.composesample.dailynotesapp.composecomponents.setupHorizontalPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class LoginSignUpScreen : AppCompatActivity() {

    private lateinit var coroutineScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    setupLoginOrSignUpUI()
                }
            }
        )
    }

    @OptIn(ExperimentalPagerApi::class)
    @Preview
    @Composable
    fun setupLoginOrSignUpUI()
    {
        coroutineScope = rememberCoroutineScope()
        val horizontalPagerState = rememberPagerState(initialPage = 0)

        Scaffold(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            content = { paddingValues->
                setupHorizontalPager(horizontalPagerState)
            },
            bottomBar = {
                setupBottomNavigation(horizontalPagerState,coroutineScope)
            }
        )
    }
}