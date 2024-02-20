package com.example.aston_trainee_work.presentation

import androidx.lifecycle.ViewModel
import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.IsSavedArticleUseCase
import com.example.aston_trainee_work.domain.SaveArticleUseCase
import com.github.terrakok.cicerone.Router
import java.text.SimpleDateFormat
import java.util.Locale

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

    fun convertDateTime(publishedAt: String): String? {
        val fromFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val formatter = SimpleDateFormat("MMM dd, yyyy | KK:mm a", Locale.ENGLISH)

        val publishDateTime = fromFormatter.parse(publishedAt)
        return publishDateTime?.let { formatter.format(it) }
    }
}