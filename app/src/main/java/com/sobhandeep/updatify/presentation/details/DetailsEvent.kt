package com.sobhandeep.updatify.presentation.details

import com.sobhandeep.updatify.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article): DetailsEvent()
    data object RemoveSideEffect: DetailsEvent()
}