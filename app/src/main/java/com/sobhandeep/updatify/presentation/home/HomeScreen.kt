package com.sobhandeep.updatify.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.sobhandeep.updatify.R
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.presentation.composables.ArticlesList
import com.sobhandeep.updatify.presentation.composables.CategoryChip
import com.sobhandeep.updatify.presentation.composables.SearchBar
import com.sobhandeep.updatify.util.Dimensions.categoryPadding
import com.sobhandeep.updatify.util.Dimensions.mediumPadding1
import com.sobhandeep.updatify.util.Dimensions.mediumPadding3
import java.util.Locale

@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
    viewModel: HomeViewModel
){

    val newsCategories: List<String> = listOf(
        "All",
        "Top Headlines",
        "Business",
        "Entertainment",
        "General",
        "Health",
        "Science",
        "Sports",
        "Technology"
    )

    var selectedCategory by remember {
        mutableStateOf(newsCategories.first())
    }

    val articles =
        when (selectedCategory) {
            newsCategories[0] -> {
                viewModel.news.collectAsLazyPagingItems()
            }
            newsCategories[1] -> {
                viewModel.topHeadlines.collectAsLazyPagingItems()
            }
            else -> {
                println(selectedCategory.lowercase(Locale.ROOT))
                viewModel.newsByCategory(category = selectedCategory.lowercase()).collectAsLazyPagingItems()
            }
        }

    val titles by remember {
        derivedStateOf {
            if(articles.itemCount > 10){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 "){
                        it.title?:""
                    }
            }
            else{
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = mediumPadding3)
//            .statusBarsPadding()
    ) {

        Image(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
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
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigateToSearch()
            },
            onSearch = {}
        )

        Spacer(modifier = Modifier.height(mediumPadding1))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = mediumPadding1)
                .basicMarquee(),
            text = titles,
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(mediumPadding1))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(categoryPadding)
        ) {

            items(newsCategories) { category ->
                CategoryChip(
                    category = category,
                    isSelected = category == selectedCategory,
                    onSelect = { selectedCategory = it }
                )
            }
        }

        ArticlesList(
            articles =  articles,
            onClick = {
                navigateToDetails(it)
            }
        )
    }
}