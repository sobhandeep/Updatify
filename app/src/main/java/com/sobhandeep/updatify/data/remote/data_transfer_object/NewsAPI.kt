package com.sobhandeep.updatify.data.remote.data_transfer_object

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("language") language: String = "en"
    ): APIResponse

    @GET("top-headlines")
    suspend fun getNewsByCategory(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): APIResponse

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
    ): APIResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("language") language: String = "en"
    ): APIResponse
}