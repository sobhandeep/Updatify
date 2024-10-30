package com.sobhandeep.updatify.presentation.bookmark

import com.sobhandeep.updatify.domain.model.Article

data class BookmarkState(

    val articles: List<Article> = emptyList()
)
