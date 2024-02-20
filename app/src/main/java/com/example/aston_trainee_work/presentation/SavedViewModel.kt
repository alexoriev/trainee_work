package com.example.aston_trainee_work.presentation

import androidx.lifecycle.ViewModel
import com.example.aston_trainee_work.common.Screens
import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.DeleteOldSavedArticlesUseCase
import com.example.aston_trainee_work.domain.GetSavedArticlesListUseCase
import com.github.terrakok.cicerone.Router

class SavedViewModel(
    private val getSavedArticlesListUseCase: GetSavedArticlesListUseCase,
    private val getDeleteOldSavedArticlesUseCase: DeleteOldSavedArticlesUseCase,
    private val router: Router
) : ViewModel() {

    init {
        deleteOldSavedArticles()
    }

    fun getSavedArticlesList() = getSavedArticlesListUseCase.getSavedArticlesList()

    fun goToArticleProfile(articleItem: ArticleItem) {
        router.navigateTo(Screens.articleProfile(articleItem))
    }

    private fun deleteOldSavedArticles() {
        getDeleteOldSavedArticlesUseCase.deleteOldSavedArticles()
    }
}