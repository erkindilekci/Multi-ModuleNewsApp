package com.erkindilekci.search_presentation

import com.erkindilekci.search_domain.model.Article

data class SearchUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val searchedArticles: List<Article>? = null
)
