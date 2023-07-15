package com.erkindilekci.news_data.network

import com.erkindilekci.common_utils.Constants
import com.erkindilekci.news_data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNewsArticles(
        @Query("country") country: String = Constants.COUNTRY,
        @Query("category") category: String = Constants.CATEGORY,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse
}
