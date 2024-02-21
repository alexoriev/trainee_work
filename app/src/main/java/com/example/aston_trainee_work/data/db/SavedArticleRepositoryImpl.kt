package com.example.aston_trainee_work.data.db

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.aston_trainee_work.BuildConfig
import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.ArticleSource
import com.example.aston_trainee_work.domain.SavedArticleRepository
import com.example.aston_trainee_work.domain.SourceImageRepository
import java.time.LocalDateTime
import javax.inject.Inject

class SavedArticleRepositoryImpl @Inject constructor(
    private val savedArticleDao: SavedArticleDao,
    private val sourceImageRepository: SourceImageRepository
) : SavedArticleRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun insert(articleItem: ArticleItem) {
        val source = ArticleSourceEmbeddable(articleItem.source.id, articleItem.source.name)
        savedArticleDao.insert(
            SavedArticleEntity(
                source,
                articleItem.author,
                articleItem.title,
                articleItem.description,
                articleItem.url,
                articleItem.urlToImage,
                articleItem.publishedAt,
                articleItem.content,
                LocalDateTime.now()
            )
        )
    }

    override fun getAll(): List<ArticleItem> {
        return mapToArticlesItemList(savedArticleDao.getAll())
    }

    override fun getByUrl(url: String): List<ArticleItem> {
        return mapToArticlesItemList(savedArticleDao.getByUrl(url))
    }

    override fun getByQuery(query: String): List<ArticleItem> {
        return mapToArticlesItemList(savedArticleDao.getByQuery("%$query%"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun deleteOld() {
        val twoWeeksAgo = LocalDateTime.now().minusDays(BuildConfig.TWO_WEEKS)
        val savedArticles = savedArticleDao.getAll()
        val urlsToDelete = savedArticles
            .filter {
                it.savedAt.isBefore(twoWeeksAgo)
            }.map {
                it.url
            }
        savedArticleDao.deleteByUrls(urlsToDelete)
    }

    private fun mapToArticlesItemList(
        articlesEntitiesList: List<SavedArticleEntity>
    ): List<ArticleItem> {
        return articlesEntitiesList.map {
            var sourceImage: Int? = null
            if (it.source.id != null) {
                sourceImage = sourceImageRepository.getImageResourceIdBySourceId(it.source.id)
            }
            val source = ArticleSource(it.source.id, it.source.name, sourceImage)
            ArticleItem(
                source,
                it.author,
                it.title,
                it.description,
                it.url,
                it.urlToImage,
                it.publishedAt,
                it.content
            )
        }
    }
}