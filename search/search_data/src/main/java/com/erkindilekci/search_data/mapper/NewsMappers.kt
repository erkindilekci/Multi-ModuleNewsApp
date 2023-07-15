package com.erkindilekci.search_data.mapper

import com.erkindilekci.search_data.model.ArticleDto
import com.erkindilekci.search_domain.model.Article

fun ArticleDto.toArticle(): Article = Article(
    author ?: "",
    content ?: "",
    description ?: "",
    title ?: "",
    urlToImage ?: ""
)
