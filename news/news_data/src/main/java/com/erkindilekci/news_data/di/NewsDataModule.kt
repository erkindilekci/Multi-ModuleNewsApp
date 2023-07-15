package com.erkindilekci.news_data.di

import com.erkindilekci.news_data.local.NewsDao
import com.erkindilekci.news_data.network.NewsApi
import com.erkindilekci.news_data.repository.NewsRepositoryImpl
import com.erkindilekci.news_domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NewsDataModule {

    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    fun provideNewsRepository(
        api: NewsApi,
        dao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(api, dao)
}
