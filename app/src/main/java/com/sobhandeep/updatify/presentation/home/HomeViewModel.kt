package com.sobhandeep.updatify.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sobhandeep.updatify.domain.instances.news.NewsInstances
import com.sobhandeep.updatify.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsInstances: NewsInstances,
): ViewModel() {

    val news = newsInstances.getNews(
        sources = listOf("bbc-news","abc-news","al-jazeera-english", "cnn", "google-news-in")
    ).cachedIn(viewModelScope)

    val topHeadlines = newsInstances.getTopHeadlines(
        sources = listOf("bbc-news","abc-news","al-jazeera-english", "cnn", "google-news-in")
    )

    fun newsByCategory(category: String): Flow<PagingData<Article>> {
        return newsInstances.getNewsByCategory(
            category = category
        ).cachedIn(viewModelScope)
    }
}