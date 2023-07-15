package com.erkindilekci.presentation

import com.erkindilekci.news_domain.model.Article

data class NewsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val articles: List<Article>? = null
)
