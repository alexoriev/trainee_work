package com.example.aston_trainee_work.domain

data class ArticlesListWrapper(
    val isSuccessful: Boolean,
    val articles: List<ArticleItem>
)
