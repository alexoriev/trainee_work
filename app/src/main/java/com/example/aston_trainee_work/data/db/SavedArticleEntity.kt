package com.example.aston_trainee_work.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class SavedArticleEntity(
    @Embedded
    val source: ArticleSourceEmbeddable,
    val author: String?,
    val title: String?,
    val description: String?,
    @PrimaryKey
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val savedAt: LocalDateTime
)

data class ArticleSourceEmbeddable(
    val id: String?,
    val name: String?
)