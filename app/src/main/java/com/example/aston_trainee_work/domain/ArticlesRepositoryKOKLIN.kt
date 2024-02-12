package com.example.aston_trainee_work.domain

interface ArticlesRepositoryKOKLIN {

    suspend fun getHeadlinesNewsList(category: Category): List<ArticleItem>
}