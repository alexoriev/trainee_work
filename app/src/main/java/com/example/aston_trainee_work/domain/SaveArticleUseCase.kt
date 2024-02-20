package com.example.aston_trainee_work.domain

import javax.inject.Inject

class SaveArticleUseCase @Inject constructor(
    private val repository: SavedArticleRepository
) {
    fun saveArticle(articleItem: ArticleItem) {
        repository.insert(articleItem)
    }
}