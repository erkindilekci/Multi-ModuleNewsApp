package com.erkindilekci.search_data.model

data class NewsResponse(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)
