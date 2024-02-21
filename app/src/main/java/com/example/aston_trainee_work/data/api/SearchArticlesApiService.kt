package com.example.aston_trainee_work.data.api

import com.example.aston_trainee_work.BuildConfig
import com.example.aston_trainee_work.data.dto.ArticlesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchArticlesApiService {
    @GET("everything?pageSize=" + BuildConfig.PAGE_SIZE + "&apiKey=" + BuildConfig.API_KEY)
    suspend fun getArticles(@Query("q") query: String,
                            @Query("page") page: Int): Response<ArticlesListResponse>
}