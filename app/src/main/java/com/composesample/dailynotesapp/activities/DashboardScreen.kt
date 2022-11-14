package com.composesample.dailynotesapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.composesample.dailynotesapp.R
import com.composesample.dailynotesapp.composecomponents.setupTopBar
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
        Scaffold(
            topBar = {
                setupTopBar(stringResource(R.string.text_dashboard_title))
            },
            content = { paddingValues ->

            }
        )
    }
}