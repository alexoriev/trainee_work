package com.example.aston_trainee_work.domain

import javax.inject.Inject

class GetHeadlinesArticlesListUseCaseKOKLIN @Inject constructor(
    private val articlesRepository: ArticlesRepositoryKOKLIN) {

    suspend fun getHeadlinesArticlesList(category: Category): List<ArticleItem> {
        return articlesRepository.getHeadlinesNewsList(category)
    }
}