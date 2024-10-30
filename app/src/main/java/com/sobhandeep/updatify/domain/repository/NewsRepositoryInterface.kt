package com.sobhandeep.updatify.domain.repository

import androidx.paging.PagingData
import com.sobhandeep.updatify.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryInterface {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun getNewsByCategory(category: String): Flow<PagingData<Article>>

    fun getTopHeadlines(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectArticles(): Flow<List<Article>>

    suspend fun selectArticle(url: String): Article?
}