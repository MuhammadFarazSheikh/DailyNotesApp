package com.composesample.dailynotesapp

sealed class BottomNavigationTabs(var title:String) {
    object Login:BottomNavigationTabs("Login")
    object SignUp:BottomNavigationTabs("SignUp")
}