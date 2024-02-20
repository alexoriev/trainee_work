package com.example.aston_trainee_work.presentation

import androidx.lifecycle.ViewModel
import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.IsSavedArticleUseCase
import com.example.aston_trainee_work.domain.SaveArticleUseCase
import com.github.terrakok.cicerone.Router

class ArticleProfileViewModel(
    private val router: Router,
    private val saveArticleUseCase: SaveArticleUseCase,
    private val isSavedArticleUseCase: IsSavedArticleUseCase,
    private val articleItem: ArticleItem
) : ViewModel() {

    fun onBackPressed() {
        router.exit()
    }

    fun saveArticle() {
        saveArticleUseCase.saveArticle(articleItem)
    }

    fun isSavedArticle(): Boolean {
        return isSavedArticleUseCase.isSavedArticle(articleItem)
    }
}