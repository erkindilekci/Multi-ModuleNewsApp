package com.erkindilekci.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.common_utils.Resource
import com.erkindilekci.news_domain.usecase.GetNewsArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsArticle: GetNewsArticleUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState())
    val state: StateFlow<NewsUiState> = _state.asStateFlow()

    init {
        getNewsArticles()
    }

    private fun getNewsArticles() {
        getNewsArticle().onEach { resource ->
            when (resource) {
                is Resource.Error -> _state.value = NewsUiState(error = resource.message)
                is Resource.Loading -> _state.value = NewsUiState(isLoading = true)
                is Resource.Success -> _state.value = NewsUiState(articles = resource.data)
            }
        }.launchIn(viewModelScope)
    }
}
