package com.erkindilekci.news_domain.di

import com.erkindilekci.news_domain.repository.NewsRepository
import com.erkindilekci.news_domain.usecase.GetNewsArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsDomainModule {

    @Provides
    @Singleton
    fun provideGetNewsArticleUseCase(repository: NewsRepository): GetNewsArticleUseCase {
        return GetNewsArticleUseCase(repository)
    }
}
