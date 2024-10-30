package com.sobhandeep.updatify.domain.instances.news

import androidx.paging.PagingData
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.domain.repository.NewsRepositoryInterface
import kotlinx.coroutines.flow.Flow

class SearchNews(

    private val newsRepository: NewsRepositoryInterface
) {

    operator fun invoke(query: String, sources: List<String>): Flow<PagingData<Article>>{
        return newsRepository.searchNews(
            query = query,
            sources = sources
        )
    }
}