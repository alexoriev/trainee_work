package com.example.aston_trainee_work.data;

import com.example.aston_trainee_work.data.api.ArticlesApiServiceJava;
import com.example.aston_trainee_work.data.dto.HeadlinesResponse;
import com.example.aston_trainee_work.domain.ArticlesRepositoryJava;
import com.example.aston_trainee_work.domain.Category;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class ArticlesRepositoryImplJava implements ArticlesRepositoryJava {

    ArticlesApiServiceJava apiService;

    @Inject
    public ArticlesRepositoryImplJava(ArticlesApiServiceJava apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<HeadlinesResponse> getHeadlinesArticlesList(Category category) {
        return apiService.getTopHeadlinesNews(category, 1);
    }
}
