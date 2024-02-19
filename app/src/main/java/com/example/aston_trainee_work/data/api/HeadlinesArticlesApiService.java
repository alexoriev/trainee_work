package com.example.aston_trainee_work.data.api;

import com.example.aston_trainee_work.BuildConfig;
import com.example.aston_trainee_work.data.dto.HeadlinesResponse;
import com.example.aston_trainee_work.domain.Category;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeadlinesArticlesApiService {
    @GET("top-headlines?pageSize=" + BuildConfig.PAGE_SIZE + "&apiKey=" + BuildConfig.API_KEY)
    Single<HeadlinesResponse> getTopHeadlinesNews(
            @Query("category")Category category,
            @Query("page")Integer page
    );
}
