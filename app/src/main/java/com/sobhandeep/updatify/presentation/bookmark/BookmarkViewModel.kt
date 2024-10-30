package com.sobhandeep.updatify.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobhandeep.updatify.domain.instances.news.NewsInstances
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsInstances: NewsInstances
): ViewModel() {

    private val _state = mutableStateOf(BookmarkState())

    val state: State<BookmarkState> = _state

    init {
        getArticles()
    }

    private fun getArticles(){
        newsInstances.selectArticles().onEach{
            _state.value = state.value.copy(articles = it.asReversed())
        }.launchIn(viewModelScope)
    }
}