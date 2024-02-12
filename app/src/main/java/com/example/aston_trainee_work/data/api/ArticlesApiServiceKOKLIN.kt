package com.example.aston_trainee_work.data.api

import com.example.aston_trainee_work.BuildConfig
import com.example.aston_trainee_work.data.dto.HeadlinesResponse
import com.example.aston_trainee_work.domain.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApiServiceKOKLIN {

    @GET("top-headlines?pageSize=${BuildConfig.PAGE_SIZE}&apiKey=${BuildConfig.API_KEY}")
    suspend fun getTopHeadlinesNews(
        @Query("category") category: Category,
        @Query("page") page: Int
    ): Response<HeadlinesResponse>
}