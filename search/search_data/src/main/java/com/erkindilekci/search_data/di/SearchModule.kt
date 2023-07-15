package com.erkindilekci.search_data.di

import com.erkindilekci.search_data.network.SearchApi
import com.erkindilekci.search_data.repostitory.SearchRepositoryImpl
import com.erkindilekci.search_domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi = retrofit.create(SearchApi::class.java)

    @Provides
    fun provideSearchRepository(api: SearchApi): SearchRepository = SearchRepositoryImpl(api)
}
