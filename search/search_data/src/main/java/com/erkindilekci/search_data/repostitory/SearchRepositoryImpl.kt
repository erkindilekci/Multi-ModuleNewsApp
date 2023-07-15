package com.erkindilekci.search_data.repostitory

import com.erkindilekci.search_data.mapper.toArticle
import com.erkindilekci.search_data.network.SearchApi
import com.erkindilekci.search_domain.model.Article
import com.erkindilekci.search_domain.repository.SearchRepository

class SearchRepositoryImpl(private val api: SearchApi) : SearchRepository {

    override suspend fun getSearchedArticles(map: MutableMap<String, String>): List<Article> {
        return api.getSearchedArticles(map).articles.map { it.toArticle() }
    }
}
