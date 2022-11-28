package com.composesample.dailynotesapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.composesample.dailynotesapp.R
import com.composesample.dailynotesapp.presentation.composablescreens.setupDrawerMenu
import com.composesample.dailynotesapp.presentation.composablescreens.setupNavigationComponent
import com.composesample.dailynotesapp.presentation.composablescreens.setupTopBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply{
                setContent {
                    dashboardSetup()
                }
            }
        )
    }

    @Composable
    @Preview
    fun dashboardSetup()
    {
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState(DrawerState(initialValue = DrawerValue.Closed))
        Scaffold(
            topBar = {
                setupTopBar(
                    stringResource(R.string.text_dashboard_title),
                    coroutineScope,
                    scaffoldState
                )
            },
            content = { paddingValues ->
                setupNavigationComponent(navController,coroutineScope)
            },
            scaffoldState = scaffoldState,
            drawerContent = {
                setupDrawerMenu(scaffoldState,coroutineScope)
            },
            drawerShape = RoundedCornerShape(
                topEnd = 10.dp,
                bottomEnd = 10.dp
            )
        )
    }
}