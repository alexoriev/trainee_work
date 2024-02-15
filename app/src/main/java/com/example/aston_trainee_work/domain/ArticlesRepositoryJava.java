package com.example.aston_trainee_work.domain;

import com.example.aston_trainee_work.data.dto.HeadlinesResponse;

import io.reactivex.rxjava3.core.Single;

public interface ArticlesRepositoryJava {
    Single<HeadlinesResponse> getHeadlinesArticlesList(Category category, Integer page);
}
