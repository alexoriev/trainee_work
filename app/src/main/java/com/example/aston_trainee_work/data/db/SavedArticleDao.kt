package com.example.aston_trainee_work.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SavedArticleDao {

    @Insert
    fun insert(savedArticle: SavedArticleEntity)

    @Query("SELECT * FROM SavedArticleEntity")
    fun getAll(): List<SavedArticleEntity>

    @Query("SELECT * FROM SavedArticleEntity WHERE url = :url")
    fun getByUrl(url: String): List<SavedArticleEntity>

    @Query("SELECT * FROM SavedArticleEntity WHERE title LIKE :query OR description LIKE :query OR content LIKE :query")
    fun getByQuery(query: String): List<SavedArticleEntity>

    @Query("DELETE FROM SavedArticleEntity WHERE url in (:urlList)")
    fun deleteByUrls(urlList: List<String>)
}