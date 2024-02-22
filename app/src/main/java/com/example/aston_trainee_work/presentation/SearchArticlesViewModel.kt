package com.example.aston_trainee_work.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aston_trainee_work.common.Screens
import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.SearchArticlesUseCase
import com.example.aston_trainee_work.domain.SearchSavedArticlesUseCase
import com.example.aston_trainee_work.utils.InternetConnectionChecker
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch

class SearchArticlesViewModel(
    private val searchArticlesUseCase: SearchArticlesUseCase,
    private val searchSavedArticlesUseCase: SearchSavedArticlesUseCase,
    private val router: Router
): ViewModel() {
    val data = MutableLiveData<List<ArticleItem>>()

    fun goToArticleProfile(articleItem: ArticleItem) {
        router.navigateTo(Screens.articleProfile(articleItem))
    }

    fun loadFirstPage(query: String, page: Int) {
        if (!InternetConnectionChecker.isConnected()) {
            data.value = searchSavedArticlesUseCase.searchSavedArticles(query)
        } else {
            viewModelScope.launch {
                try {
                    val wrapper = searchArticlesUseCase.searchArticles(query, page)
                    if (wrapper.isSuccessful) {
                        data.value = wrapper.articles
                    } else {
                        router.navigateTo(Screens.error())
                    }
                } catch (e: Exception) {
                    router.navigateTo(Screens.error())
                }
            }
        }
    }
}