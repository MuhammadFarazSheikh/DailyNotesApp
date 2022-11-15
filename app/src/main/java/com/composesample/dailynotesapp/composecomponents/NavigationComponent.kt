package com.composesample.dailynotesapp.composecomponents

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.composesample.dailynotesapp.activities.Constants.Companion.ADD_TO_DAILY_NOTES_ROUTE
import com.composesample.dailynotesapp.activities.Constants.Companion.DAILY_NOTES_LIST_ROUTE

@Composable
fun setupNavigationComponent(navController: NavHostController)
{
    NavHost(startDestination = ADD_TO_DAILY_NOTES_ROUTE, navController = navController, builder = {
        composable(ADD_TO_DAILY_NOTES_ROUTE){
            addToDailyNotes(navController)
        }
        composable(DAILY_NOTES_LIST_ROUTE){
            addToDailyNotes(navController)
        }
    })
}