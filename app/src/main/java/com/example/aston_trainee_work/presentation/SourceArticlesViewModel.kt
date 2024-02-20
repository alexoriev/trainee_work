package com.example.aston_trainee_work.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aston_trainee_work.common.Screens.articleProfile
import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.GetSourceArticlesListUseCase
import com.example.aston_trainee_work.domain.SourceItem
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch

class SourceArticlesViewModel(
    private val getSourceArticlesListUseCase: GetSourceArticlesListUseCase,
    private val router: Router,
    private val source: SourceItem
): ViewModel() {
    val data = MutableLiveData<List<ArticleItem>>()

    fun goToArticleProfile(articleItem: ArticleItem) {
        router.navigateTo(articleProfile(articleItem))
    }

    fun loadFirstPage(page: Int) {
        viewModelScope.launch {
            data.value = getSourceArticlesListUseCase.getSourceArticlesList(source, page)
        }
    }

    fun loadNextPage(page: Int) {
        viewModelScope.launch {
            val nextPageData = getSourceArticlesListUseCase.getSourceArticlesList(source, page)
            data.value = data.value?.plus(nextPageData)
        }
    }
}