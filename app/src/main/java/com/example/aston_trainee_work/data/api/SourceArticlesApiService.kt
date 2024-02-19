package com.example.aston_trainee_work.data.api

import com.example.aston_trainee_work.BuildConfig
import com.example.aston_trainee_work.data.dto.ArticlesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SourceArticlesApiService {
    @GET("everything?pageSize=" + BuildConfig.PAGE_SIZE + "&apiKey=" + BuildConfig.API_KEY)
    suspend fun getSourceArticles(@Query("sources")sourceId: String,
                                  @Query("page")page: Int): Response<ArticlesListResponse>
}
