package com.erkindilekci.search_domain.repository

import com.erkindilekci.search_domain.model.Article

interface SearchRepository {

    suspend fun getSearchedArticles(map: MutableMap<String, String>): List<Article>
}
