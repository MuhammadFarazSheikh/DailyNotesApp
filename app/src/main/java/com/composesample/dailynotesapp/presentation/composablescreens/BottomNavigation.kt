package com.composesample.dailynotesapp.presentation.composablescreens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.composesample.dailynotesapp.BottomNavigationTabs
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun setupBottomNavigation(pagerState: PagerState,coroutineScope: CoroutineScope)
{
    val bottomNavTabsList = arrayListOf<BottomNavigationTabs>(BottomNavigationTabs.Login,BottomNavigationTabs.SignUp)
    BottomNavigation(
        content = {
            bottomNavTabsList.forEachIndexed { index, bottomNavigationTabs ->
                BottomNavigationItem(
                    selected = pagerState.currentPage==index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    icon = {},
                    label ={
                        Text(
                            bottomNavigationTabs.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    selectedContentColor = Color.DarkGray,
                    unselectedContentColor = Color.Gray
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = Color.LightGray
    )
}