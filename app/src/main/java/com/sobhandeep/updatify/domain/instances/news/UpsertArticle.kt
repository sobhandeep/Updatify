package com.sobhandeep.updatify.domain.instances.news

import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.domain.repository.NewsRepositoryInterface

class UpsertArticle(

    private val newsRepository: NewsRepositoryInterface
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}