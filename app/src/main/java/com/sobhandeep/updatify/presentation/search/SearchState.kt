package com.sobhandeep.updatify.presentation.search

import androidx.paging.PagingData
import com.sobhandeep.updatify.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(

    val query: String = "",
    val articles: Flow<PagingData<Article>>? = null
)
