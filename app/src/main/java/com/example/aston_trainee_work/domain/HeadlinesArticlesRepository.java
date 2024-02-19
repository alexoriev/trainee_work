package com.example.aston_trainee_work.domain;

import com.example.aston_trainee_work.data.dto.ArticlesListResponse;

import io.reactivex.rxjava3.core.Single;

public interface HeadlinesArticlesRepository {
    Single<ArticlesListResponse> getHeadlinesArticlesList(Category category, Integer page);
}
