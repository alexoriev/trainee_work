package com.example.aston_trainee_work.domain

data class ArticleItem(
    val source: SourceItem,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
)