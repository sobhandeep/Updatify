package com.sobhandeep.updatify.domain.instances.news

import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.domain.repository.NewsRepositoryInterface

class SelectArticle(

    private val newsRepository: NewsRepositoryInterface
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}