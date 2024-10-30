package com.sobhandeep.updatify.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sobhandeep.updatify.domain.instances.news.NewsInstances
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsInstances: NewsInstances
): ViewModel() {

    private val _state = mutableStateOf(SearchState())

    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.UpdateQuery -> {
                _state.value = state.value.copy(query = event.query)
            }
            is SearchEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews(){
        val articles = newsInstances.searchNews(
            query = state.value.query,
            sources = listOf("bbc-news","abc-news","al-jazeera-english", "cnn")
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }
}