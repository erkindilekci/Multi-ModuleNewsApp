package com.erkindilekci.search_domain.usecase

import com.erkindilekci.common_utils.Resource
import com.erkindilekci.search_domain.model.Article
import com.erkindilekci.search_domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSearchedArticlesUseCase(private val repository: SearchRepository) {

    operator fun invoke(map: MutableMap<String, String>): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = repository.getSearchedArticles(map)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}
