package com.composesample.dailynotesapp.composecomponents

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun setupHorizontalPager(pagerState: PagerState)
{
    HorizontalPager(
        count = 2,
        content ={ index->
            when(index)
            {
                0-> setupLoginScreen()
                1-> setupLoginScreen()
            }
        },
        state = pagerState,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    )
}