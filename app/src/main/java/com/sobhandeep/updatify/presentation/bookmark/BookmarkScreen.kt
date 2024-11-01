package com.sobhandeep.updatify.presentation.bookmark

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.sobhandeep.updatify.R
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.presentation.composables.ArticlesList
import com.sobhandeep.updatify.util.Dimensions.extraSmallPadding2
import com.sobhandeep.updatify.util.Dimensions.mediumPadding1
import com.sobhandeep.updatify.util.Dimensions.mediumPadding3
import kotlin.math.roundToInt

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit,
    navigateToHome: () -> Unit
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

        Text(
            modifier = Modifier.padding(start = extraSmallPadding2),
            text = "Bookmarks",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(mediumPadding1))

        ArticlesList(
            articles = state.articles,
            onClick = {
                navigateToDetails(it)
            }
        )
    }
}