package com.erkindilekci.multimodulenewsapp.di

import android.content.Context
import androidx.room.Room
import com.erkindilekci.common_utils.Navigator
import com.erkindilekci.multimodulenewsapp.navigation.DefaultNavigator
import com.erkindilekci.news_data.local.NewsDao
import com.erkindilekci.news_data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProvider(): Navigator.Provider {
        return DefaultNavigator()
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        "news_db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNewsDao(db: NewsDatabase): NewsDao = db.newsDao()
}
