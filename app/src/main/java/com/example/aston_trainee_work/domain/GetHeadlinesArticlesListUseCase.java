package com.example.aston_trainee_work.domain;

import com.example.aston_trainee_work.data.dto.HeadlinesResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetHeadlinesArticlesListUseCase {

    ArticlesRepositoryJava articlesRepository;

    @Inject
    GetHeadlinesArticlesListUseCase(ArticlesRepositoryJava articlesRepository) {
        this.articlesRepository = articlesRepository;
    }


    public Single<HeadlinesResponse> getHeadlinesArticlesList(Category category, Integer page) {
        return articlesRepository.getHeadlinesArticlesList(category, page);
    }
}
