package com.example.aston_trainee_work.domain

interface SourceArticlesRepository {
    suspend fun getSourceArticlesList(source: SourceItem, page: Int): ArticlesListWrapper
}