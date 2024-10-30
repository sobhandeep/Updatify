package com.sobhandeep.updatify.domain.instances.news

import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.domain.repository.NewsRepositoryInterface
import kotlinx.coroutines.flow.Flow

class SelectArticles(

    private val newsRepository: NewsRepositoryInterface
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}