package com.example.aston_trainee_work.data.network;

import com.example.aston_trainee_work.data.api.HeadlinesArticlesApiService;
import com.example.aston_trainee_work.data.dto.HeadlinesResponse;
import com.example.aston_trainee_work.domain.HeadlinesArticlesRepository;
import com.example.aston_trainee_work.domain.Category;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class HeadlinesArticlesRepositoryImpl implements HeadlinesArticlesRepository {

    HeadlinesArticlesApiService apiService;

    @Inject
    public HeadlinesArticlesRepositoryImpl(HeadlinesArticlesApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<HeadlinesResponse> getHeadlinesArticlesList(Category category, Integer page) {
        return apiService.getTopHeadlinesNews(category, page);
    }
}
