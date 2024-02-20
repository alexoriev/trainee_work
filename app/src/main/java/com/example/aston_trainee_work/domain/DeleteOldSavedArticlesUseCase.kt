package com.example.aston_trainee_work.domain

import javax.inject.Inject

class DeleteOldSavedArticlesUseCase @Inject constructor(
    private val savedArticleRepository: SavedArticleRepository
) {
    fun deleteOldSavedArticles() {
        savedArticleRepository.deleteOld()
    }
}