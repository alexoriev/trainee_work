package com.example.aston_trainee_work.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aston_trainee_work.common.Screens
import com.example.aston_trainee_work.common.Screens.articleProfile
import com.example.aston_trainee_work.common.Screens.noInternet
import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.GetSourceArticlesListUseCase
import com.example.aston_trainee_work.domain.SourceItem
import com.example.aston_trainee_work.utils.InternetConnectionChecker.Companion.isConnected
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import java.lang.Exception

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
        if (!isConnected()) {
            router.navigateTo(noInternet())
            return
        }
        viewModelScope.launch {
            try {
                val wrapper = getSourceArticlesListUseCase.getSourceArticlesList(source, page)
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

    fun loadNextPage(page: Int) {
        if (!isConnected()) {
            router.navigateTo(noInternet())
            return
        }
        viewModelScope.launch {
            try {
                val wrapper = getSourceArticlesListUseCase.getSourceArticlesList(source, page)
                if (wrapper.isSuccessful) {
                    data.value = data.value?.plus(wrapper.articles)
                } else {
                    router.navigateTo(Screens.error())
                }
            } catch (e: Exception) {
                router.navigateTo(Screens.error())
            }
        }
    }
}