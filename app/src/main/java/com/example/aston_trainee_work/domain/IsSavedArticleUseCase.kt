package com.example.aston_trainee_work.domain

import javax.inject.Inject

class IsSavedArticleUseCase @Inject constructor(
    private val repository: SavedArticleRepository
) {
    fun isSavedArticle(articleItem: ArticleItem): Boolean {
        return repository.getByUrl(articleItem.url).isNotEmpty()
    }
}