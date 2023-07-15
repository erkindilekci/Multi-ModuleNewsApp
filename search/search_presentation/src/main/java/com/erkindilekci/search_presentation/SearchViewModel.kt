package com.erkindilekci.search_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.common_utils.Resource
import com.erkindilekci.search_domain.usecase.GetSearchedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchedArticlesUseCase: GetSearchedArticlesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState> = _state.asStateFlow()

    fun getSearchedArticles(map: MutableMap<String, String>) {
        getSearchedArticlesUseCase(map).onEach { resource ->
            when (resource) {
                is Resource.Error -> _state.value = SearchUiState(error = resource.message)
                is Resource.Loading -> _state.value = SearchUiState(isLoading = true)
                is Resource.Success -> _state.value =
                    SearchUiState(searchedArticles = resource.data)
            }
        }.launchIn(viewModelScope)
    }
}
