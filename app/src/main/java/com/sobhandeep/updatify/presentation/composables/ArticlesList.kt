package com.sobhandeep.updatify.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.util.Dimensions.extraSmallPadding2
import com.sobhandeep.updatify.util.Dimensions.mediumPadding1

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick:(Article) -> Unit
) {

    val handlePagingResult = handlePagingResult(articles)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(mediumPadding1),
            contentPadding = PaddingValues(all = extraSmallPadding2)
        ) {
            items(
                count = articles.itemCount,
            ) { it ->
                articles[it]?.let {
                    NewsCard(article = it, onClick = {onClick(it)})
                }
            }
        }
    }
}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick:(Article) -> Unit
) {
    if(articles.isEmpty()){
        EmptyPage(
            emptyMessage = "You have not bookmarked any articles."
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(mediumPadding1),
        contentPadding = PaddingValues(all = extraSmallPadding2)
    ) {
        items(
            count = articles.size,
        ) {
            val article = articles[it]
            NewsCard(
                article = article,
                onClick = {
                    onClick(article)
                }
            )
        }
    }
}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {

    val loadState = articles.loadState

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        articles.itemCount == 0 -> {
            EmptyPage(
                emptyMessage = "No Search Results",
                error = error
            )
            false
        }

        error != null -> {
            EmptyPage(
                emptyMessage = "",
                error = error
            )
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {

    Column(verticalArrangement = Arrangement.spacedBy(mediumPadding1)) {
        repeat(10) {
            ArticleShimmerEffect(
                modifier = Modifier.padding(horizontal = mediumPadding1)
            )
        }
    }
}