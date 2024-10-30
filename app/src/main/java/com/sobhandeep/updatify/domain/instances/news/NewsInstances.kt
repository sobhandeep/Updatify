package com.sobhandeep.updatify.domain.instances.news

data class NewsInstances(

    val getNews: GetNews,
    val getNewsByCategory: GetNewsByCategory,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle: SelectArticle,
    val getTopHeadlines: GetTopHeadlines
)
