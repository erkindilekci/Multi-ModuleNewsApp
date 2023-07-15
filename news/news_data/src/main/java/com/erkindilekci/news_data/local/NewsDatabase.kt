package com.erkindilekci.news_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.erkindilekci.news_domain.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}
