package com.sobhandeep.updatify.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sobhandeep.updatify.data.remote.data_transfer_object.NewsAPI
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.util.Constants.API_KEY

class NewsPagingSource(
    private val newsAPI: NewsAPI,
    private val sources: String
): PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article>{
        val page = params.key?:1

        return try{
            val newsResponse = newsAPI.getNews(
                page = page,
                sources = sources,
                apiKey = API_KEY
            )

            totalNewsCount += newsResponse.articles.size

            val articles = newsResponse.articles.distinctBy {
                it.title
            }

            LoadResult.Page(
                data = articles,
                nextKey = if(totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }

        catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}