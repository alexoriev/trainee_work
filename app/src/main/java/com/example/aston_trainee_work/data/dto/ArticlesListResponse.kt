package com.example.aston_trainee_work.data.dto

data class ArticlesListResponse(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)