package com.sobhandeep.updatify.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.sobhandeep.updatify.R
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.presentation.composables.ArticlesList
import com.sobhandeep.updatify.presentation.composables.SearchBar
import com.sobhandeep.updatify.util.Dimensions.mediumPadding1
import com.sobhandeep.updatify.util.Dimensions.mediumPadding3
import kotlin.math.roundToInt

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit,
    navigateToHome: () -> Unit,
    navigateToBookmark: () -> Unit
){

    val offsetX by remember {
        mutableFloatStateOf(0f)
    }

    val offsetY by remember {
        mutableFloatStateOf(0f)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = mediumPadding3)
            .offset {
                IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
            }
            .pointerInput(Unit){
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    val (x) = dragAmount

                    when {
                        x > 0 -> {
                            navigateToHome()
                        }
                        x < 0 -> {
                            navigateToBookmark()
                        }
                    }
                }
            }
    ) {

        Image(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .clickable {
                    navigateToHome()
                },
            painter = painterResource(
                id = if(isSystemInDarkTheme()) {
                    R.drawable.logo_dark
                }
                else{
                    R.drawable.logo_light
                }
            ),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(mediumPadding1))

        SearchBar(
            text = state.query,
            readOnly = false,
            onValueChange = {
                event(SearchEvent.UpdateQuery(it))
            },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )

        Spacer(modifier = Modifier.height(mediumPadding1))

        state.articles?.let { it ->
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(
                articles = articles,
                onClick = {
                    navigateToDetails(it)
                }
            )
        }
    }
}