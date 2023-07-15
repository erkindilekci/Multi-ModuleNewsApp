package com.erkindilekci.search_data.network

import com.erkindilekci.search_data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchApi {

    @GET("everything")
    suspend fun getSearchedArticles(
        @QueryMap map: MutableMap<String, String>
    ): NewsResponse
}
