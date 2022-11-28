package com.composesample.dailynotesapp.presentation.composablescreens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalPagerApi::class)
@Composable
fun setupHorizontalPager(pagerState: PagerState,coroutineScope: CoroutineScope)
{
    HorizontalPager(
        count = 2,
        content ={ index->
            when(index)
            {
                0-> setupLoginScreen(coroutineScope)
                1-> setupSignUpScreen(coroutineScope)
            }
        },
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}