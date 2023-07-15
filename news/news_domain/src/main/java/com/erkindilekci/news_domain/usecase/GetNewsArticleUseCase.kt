package com.erkindilekci.news_domain.usecase

import com.erkindilekci.common_utils.Resource
import com.erkindilekci.news_domain.model.Article
import com.erkindilekci.news_domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNewsArticleUseCase(private val repository: NewsRepository) {

    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = repository.getNewsArticle()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}
