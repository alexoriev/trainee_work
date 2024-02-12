package com.example.aston_trainee_work.data.dto

data class HeadlinesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)