package com.example.aston_trainee_work.data.dto

import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.ArticleSource

data class Article(
    val source: ArticleSourceDto,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
) {
    fun fromDto(source: ArticleSource) =
        ArticleItem(source, author, title,
                    description, url, urlToImage,
                    publishedAt, content)
}