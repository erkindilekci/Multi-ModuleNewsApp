package com.erkindilekci.news_data.repository

import com.erkindilekci.news_data.local.NewsDao
import com.erkindilekci.news_data.mapper.toArticle
import com.erkindilekci.news_data.network.NewsApi
import com.erkindilekci.news_domain.model.Article
import com.erkindilekci.news_domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val api: NewsApi,
    private val dao: NewsDao
) : NewsRepository {

    override suspend fun getNewsArticle(): List<Article> {
        return try {
            val tempArticles = api.getNewsArticles().articles.map { it.toArticle() }
            dao.upsertArticles(tempArticles)
            dao.getNewsArticle()
        } catch (e: Exception) {
            dao.getNewsArticle()
        }
    }
}
