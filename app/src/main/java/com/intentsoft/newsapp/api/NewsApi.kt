package com.intentsoft.newsapp.api

import com.intentsoft.newsapp.models.NewsResponse
import com.intentsoft.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Zokirjon
 * @date 11/24/2021
 */
interface NewsApi {

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country")
        countryCode: String = "us"
    ): Response<NewsResponse>
}