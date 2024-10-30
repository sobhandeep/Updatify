package com.sobhandeep.updatify.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sobhandeep.updatify.presentation.composables.NewsButton
import com.sobhandeep.updatify.presentation.composables.NewsTextButton
import com.sobhandeep.updatify.util.Dimensions.indicatorWidth
import com.sobhandeep.updatify.util.Dimensions.mediumPadding2
import com.sobhandeep.updatify.presentation.composables.Indicator
import com.sobhandeep.updatify.presentation.composables.OnboardingPage
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    event: (OnboardingEvent) -> Unit
){

    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }

    val buttonState = remember {
        derivedStateOf {
            when(pagerState.currentPage){
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Finish")
                else -> listOf("", "")
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HorizontalPager(
            state = pagerState
        ) {
            index ->
            OnboardingPage(
                modifier = Modifier.fillMaxWidth(),
                page = pages[index]
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Indicator(
                modifier = Modifier.width(indicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                val scope = rememberCoroutineScope()

                if(buttonState.value[0].isNotEmpty()){
                    NewsTextButton(buttonState.value[0]) {
                        scope.launch{
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    }
                }

                NewsButton(buttonState.value[1]) {
                    scope.launch{
                        if(pagerState.currentPage == 2){
                            event(OnboardingEvent.SaveAppEntry)
                        }
                        else{
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(.5f))
    }
}