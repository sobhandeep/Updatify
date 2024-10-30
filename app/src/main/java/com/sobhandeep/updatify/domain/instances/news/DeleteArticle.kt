package com.sobhandeep.updatify.domain.instances.news

import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.domain.repository.NewsRepositoryInterface

class DeleteArticle(

    private val newsRepository: NewsRepositoryInterface
) {

    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}