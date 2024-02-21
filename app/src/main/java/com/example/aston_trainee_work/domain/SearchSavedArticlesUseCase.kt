package com.example.aston_trainee_work.domain

import javax.inject.Inject

class SearchSavedArticlesUseCase @Inject constructor(
    private val savedArticleRepository: SavedArticleRepository
) {
    fun searchSavedArticles(query: String): List<ArticleItem> {
        return savedArticleRepository.getByQuery(query)
    }
}