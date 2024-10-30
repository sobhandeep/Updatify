package com.sobhandeep.updatify.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobhandeep.updatify.domain.instances.news.NewsInstances
import com.sobhandeep.updatify.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsInstances: NewsInstances
): ViewModel()  {

    var sideEffect by mutableStateOf<String?>(null)

    var isBookmarked = mutableStateOf(false)

    fun onEvent(event: DetailsEvent){

        when(event){
            is DetailsEvent.UpsertDeleteArticle ->{
                viewModelScope.launch {
                    val article = newsInstances.selectArticle(url = event.article.url)
                    isBookmarked.value = event.article.isBookmarked
                    isBookmarked.value = newsInstances.selectArticle(event.article.url) != null
                    if(article == null){
                        upsertArticle(event.article)
                    }
                    else{
                        deleteArticle(event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun upsertArticle(article: Article){

        article.isBookmarked = true
        newsInstances.upsertArticle(article = article)
        sideEffect = "Article Saved"
        isBookmarked.value = true
    }

    private suspend fun deleteArticle(article: Article){

        article.isBookmarked = false
        newsInstances.deleteArticle(article = article)
        sideEffect = "Article Deleted"
        isBookmarked.value = false
    }
}