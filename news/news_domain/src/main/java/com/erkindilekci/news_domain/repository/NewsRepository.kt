package com.erkindilekci.news_domain.repository

import com.erkindilekci.news_domain.model.Article

interface NewsRepository {

    suspend fun getNewsArticle(): List<Article>
}
