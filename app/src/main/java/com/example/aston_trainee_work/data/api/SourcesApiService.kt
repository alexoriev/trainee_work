package com.example.aston_trainee_work.data.api

import com.example.aston_trainee_work.BuildConfig
import com.example.aston_trainee_work.data.dto.SourcesResponse
import retrofit2.Response
import retrofit2.http.GET

interface SourcesApiService {

    @GET("top-headlines/sources?apiKey=${BuildConfig.API_KEY}")
    suspend fun getSources(): Response<SourcesResponse>
}