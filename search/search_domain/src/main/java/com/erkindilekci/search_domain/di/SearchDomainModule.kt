package com.erkindilekci.search_domain.di

import com.erkindilekci.search_domain.repository.SearchRepository
import com.erkindilekci.search_domain.usecase.GetSearchedArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchDomainModule {

    @Provides
    fun provideSearchArticlesUseCase(repository: SearchRepository): GetSearchedArticlesUseCase {
        return GetSearchedArticlesUseCase(repository)
    }
}
