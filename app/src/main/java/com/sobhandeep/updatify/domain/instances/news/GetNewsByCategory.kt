package com.sobhandeep.updatify.domain.instances.news

import androidx.paging.PagingData
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.domain.repository.NewsRepositoryInterface
import kotlinx.coroutines.flow.Flow


class GetNewsByCategory(

    private val newsRepository: NewsRepositoryInterface
) {

    operator fun invoke(category: String): Flow<PagingData<Article>>{
        return newsRepository.getNewsByCategory(category)
    }
}