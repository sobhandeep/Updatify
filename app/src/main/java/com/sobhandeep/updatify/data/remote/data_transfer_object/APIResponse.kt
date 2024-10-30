package com.sobhandeep.updatify.data.remote.data_transfer_object

import com.sobhandeep.updatify.domain.model.Article

data class APIResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)