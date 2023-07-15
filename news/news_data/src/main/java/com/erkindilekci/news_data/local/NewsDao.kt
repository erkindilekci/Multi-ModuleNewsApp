package com.erkindilekci.news_data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.erkindilekci.news_domain.model.Article

@Dao
interface NewsDao {

    @Upsert
    suspend fun upsertArticles(articles: List<Article>)

    @Query("SELECT * FROM article")
    suspend fun getNewsArticle(): List<Article>
}
