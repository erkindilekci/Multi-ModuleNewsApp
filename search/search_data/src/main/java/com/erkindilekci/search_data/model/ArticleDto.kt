package com.erkindilekci.search_data.model

data class ArticleDto(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceDto: SourceDto?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)
