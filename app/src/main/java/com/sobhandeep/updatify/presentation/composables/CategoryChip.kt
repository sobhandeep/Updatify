package com.sobhandeep.updatify.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.sobhandeep.updatify.R
import com.sobhandeep.updatify.util.Dimensions.cardBorderRadius
import com.sobhandeep.updatify.util.Dimensions.cardElevation
import com.sobhandeep.updatify.util.Dimensions.extraSmallPadding1
import com.sobhandeep.updatify.util.Dimensions.extraSmallPadding2

@Composable
fun CategoryChip(
    category: String,
    isSelected: Boolean,
    onSelect: (String) -> Unit
) {

    val backgroundColor = if (isSelected) colorResource(
        id = R.color.category_selected) else colorResource(id = R.color.category_unselected)

    val textColor = if(isSystemInDarkTheme()){
        Color.White
    }

    else{

        if(isSelected){
            Color.White
        }

        else{
            Color.Black
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(cardBorderRadius),
        elevation = CardDefaults.cardElevation(cardElevation),
        modifier = Modifier
            .padding(vertical = extraSmallPadding1, horizontal = extraSmallPadding2)
            .clickable { onSelect(category) }
    ) {

        Text(
            text = category,
            color = textColor,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
