package com.sobhandeep.updatify.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.sobhandeep.updatify.R
import com.sobhandeep.updatify.presentation.bottom_navigation.BottomNavigationItem
import com.sobhandeep.updatify.util.Dimensions.bottomElevation
import com.sobhandeep.updatify.util.Dimensions.extraSmallPadding2
import com.sobhandeep.updatify.util.Dimensions.iconSize

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
){

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = bottomElevation
    ) {
        items.forEachIndexed{ index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = {
                    onItemClick(index)
                },
                icon = {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            modifier = Modifier.size(iconSize),
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                        )

                        Spacer(modifier = Modifier.height(extraSmallPadding2))

                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}