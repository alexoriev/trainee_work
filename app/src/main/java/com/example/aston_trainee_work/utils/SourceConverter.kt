package com.example.aston_trainee_work.utils

import com.example.aston_trainee_work.data.db.SourceImageRepository
import com.example.aston_trainee_work.data.dto.ArticleSourceDto
import com.example.aston_trainee_work.data.dto.Source
import com.example.aston_trainee_work.domain.ArticleSource
import com.example.aston_trainee_work.domain.SourceItem
import javax.inject.Inject

class SourceConverter @Inject constructor(
    private val sourceImageRepository: SourceImageRepository
) {
    fun convertArticleSource(articleSource: ArticleSourceDto): ArticleSource {
        return if (articleSource.id != null) {
            val imageSourceId = sourceImageRepository.getImageSourceById(articleSource.id)
           ArticleSource(articleSource.id, articleSource.name, imageSourceId)
        } else {
            ArticleSource(articleSource.id, articleSource.name, null)
        }
    }

    fun convertSource(source: Source): SourceItem {
        val imageSourceId = sourceImageRepository.getImageSourceById(source.id)
        return SourceItem(source.id, source.name, source.country, source.category, imageSourceId)
    }
}