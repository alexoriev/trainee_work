package com.example.aston_trainee_work.domain

interface SavedArticleRepository {

    fun insert(articleItem: ArticleItem)

    fun getAll(): List<ArticleItem>

    fun getByUrl(url: String): List<ArticleItem>
}