package com.example.aston_trainee_work.data.api

import com.example.aston_trainee_work.BuildConfig
import com.example.aston_trainee_work.data.dto.ArticlesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SearchArticlesApiService {
    @GET("everything?pageSize=" + BuildConfig.PAGE_SIZE + "&apiKey=" + BuildConfig.API_KEY)
    suspend fun searchArticles(@Query("q") query: String,
                            @Query("page") page: Int): Response<ArticlesListResponse>

    @GET("everything?pageSize=" + BuildConfig.PAGE_SIZE + "&apiKey=" + BuildConfig.API_KEY)
    suspend fun filterArticles(@QueryMap(encoded = true) filters: Map<String, String>,
                               @Query("page") page: Int): Response<ArticlesListResponse>
}