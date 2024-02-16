package com.example.aston_trainee_work.domain

import java.io.Serializable

data class ArticleItem(
    val source: SourceWithImage,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
): Serializable