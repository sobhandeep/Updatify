package com.sobhandeep.updatify.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sobhandeep.updatify.data.remote.CategoryNewsPagingSource
import com.sobhandeep.updatify.data.remote.NewsPagingSource
import com.sobhandeep.updatify.data.remote.SearchNewsPagingSource
import com.sobhandeep.updatify.data.remote.TopHeadlinesNewsPagingSource
import com.sobhandeep.updatify.data.remote.data_transfer_object.NewsAPI
import com.sobhandeep.updatify.data.room.NewsDao
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.domain.repository.NewsRepositoryInterface
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val newsAPI: NewsAPI,
    private val newsDao: NewsDao
): NewsRepositoryInterface {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsAPI = newsAPI,
                    sources = sources.joinToString(",")
                )
            }
        ).flow
    }

    override fun getNewsByCategory(category: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CategoryNewsPagingSource(
                    newsAPI = newsAPI,
                    category = category
                )
            }
        ).flow
    }

    override fun getTopHeadlines(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                TopHeadlinesNewsPagingSource(
                    sources = sources.joinToString(","),
                    newsAPI = newsAPI,
                )
            }
        ).flow
    }

    override fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    query = query,
                    newsAPI = newsAPI,
                    sources = sources.joinToString(",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}