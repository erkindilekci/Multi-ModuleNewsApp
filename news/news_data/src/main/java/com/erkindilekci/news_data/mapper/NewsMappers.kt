package com.erkindilekci.news_data.mapper

import com.erkindilekci.news_data.model.ArticleDto
import com.erkindilekci.news_domain.model.Article

fun ArticleDto.toArticle(): Article = Article(
    author ?: "",
    content ?: "",
    description ?: "",
    title ?: "",
    urlToImage ?: ""
)
