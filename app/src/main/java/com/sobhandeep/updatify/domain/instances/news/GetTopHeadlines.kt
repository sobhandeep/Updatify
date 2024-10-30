package com.sobhandeep.updatify.domain.instances.news

import androidx.paging.PagingData
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.domain.repository.NewsRepositoryInterface
import kotlinx.coroutines.flow.Flow

class GetTopHeadlines(

    private val newsRepository: NewsRepositoryInterface
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getTopHeadlines(sources = sources)
    }
}