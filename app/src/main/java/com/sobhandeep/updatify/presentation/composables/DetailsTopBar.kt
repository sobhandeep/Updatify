package com.sobhandeep.updatify.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.sobhandeep.updatify.R
import com.sobhandeep.updatify.util.Dimensions.extraSmallPadding2
import com.sobhandeep.updatify.util.Dimensions.mediumPadding2

@Composable
fun DetailsTopBar(
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = mediumPadding2),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier.padding(start = extraSmallPadding2),
            onClick = onBackClick
        ){

            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = null
            )
        }

        Row(
            modifier = Modifier.padding(end = extraSmallPadding2)
        ) {
            IconButton(onClick = onBookmarkClick) {

                Icon(
                    painter = painterResource(
                        id = if (isBookmarked) R.drawable.ic_bookmark_selected else R.drawable.ic_bookmark
                    ),
                    contentDescription = null
                )
            }

            IconButton(onClick = onShareClick) {

                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null
                )
            }
        }
    }
}