package com.sobhandeep.updatify.presentation.composables

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.sobhandeep.updatify.R
import com.sobhandeep.updatify.util.Dimensions.articleCardSize
import com.sobhandeep.updatify.util.Dimensions.extraSmallPadding1

fun Modifier.shimmerEffect() = composed {

    val transition = rememberInfiniteTransition(label = "")

    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        ), label = ""
    ).value
    background(
        color = colorResource(id = R.color.shimmer).copy(alpha = alpha)
    )
}

@Composable
fun ArticleShimmerEffect(
    modifier: Modifier
){

    Row(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .size(articleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(horizontal = extraSmallPadding1)
                .height(articleCardSize)
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = extraSmallPadding1)
                    .shimmerEffect()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = extraSmallPadding1)
                        .height(15.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}