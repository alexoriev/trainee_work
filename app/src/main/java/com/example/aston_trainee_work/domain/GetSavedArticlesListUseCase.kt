package com.example.aston_trainee_work.domain

import javax.inject.Inject

class GetSavedArticlesListUseCase @Inject constructor(
    private val savedArticleRepository: SavedArticleRepository
) {
    fun getSavedArticlesList(): List<ArticleItem> {
        return savedArticleRepository.getAll()
    }
}