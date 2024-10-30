package com.sobhandeep.updatify.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.sobhandeep.updatify.R
import com.sobhandeep.updatify.util.Dimensions.mediumPadding1
import com.sobhandeep.updatify.util.Dimensions.mediumPadding2
import com.sobhandeep.updatify.presentation.onboarding.Page

@Composable
fun OnboardingPage(
    modifier: Modifier,
    page: Page
){

    Column(
        modifier = modifier
    ) {

        Image(
            painter = painterResource(id = page.image),
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(.6f),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier.height(mediumPadding1)
        )

        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )

        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(id = R.color.text_medium)
        )
    }
}
