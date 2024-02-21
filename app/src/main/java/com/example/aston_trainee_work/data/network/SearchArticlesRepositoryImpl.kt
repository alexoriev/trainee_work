package com.example.aston_trainee_work.data.network

import com.example.aston_trainee_work.data.api.SearchArticlesApiService
import com.example.aston_trainee_work.data.dto.ArticleDto
import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.ArticleSource
import com.example.aston_trainee_work.domain.ArticlesListWrapper
import com.example.aston_trainee_work.domain.Language
import com.example.aston_trainee_work.domain.SearchArticlesRepository
import com.example.aston_trainee_work.domain.SortingType
import com.example.aston_trainee_work.domain.SourceImageRepository
import java.time.LocalDate
import javax.inject.Inject

class SearchArticlesRepositoryImpl @Inject constructor(
    private val searchArticlesApiService: SearchArticlesApiService,
    private val sourceImageRepository: SourceImageRepository
): SearchArticlesRepository {

    override suspend fun searchArticles(query: String, page: Int): ArticlesListWrapper {
        val response = searchArticlesApiService.searchArticles(query, page)
        val articles = mapToArticlesList(response.body()?.articles)
        return ArticlesListWrapper(response.isSuccessful, articles)
    }

    override suspend fun filterArticles(
        sortingType: SortingType?,
        fromDate: LocalDate?,
        toDate: LocalDate?,
        language: Language?,
        page: Int
    ): ArticlesListWrapper {
        val filtersMap = mutableMapOf<String, String>()
        if (sortingType != null) {
            val sortBy = when(sortingType) {
                SortingType.POPULAR -> "popularity"
                SortingType.NEW -> "publishedAt"
                SortingType.RELEVANT -> "relevancy"
            }
            filtersMap["sortBy"] = sortBy
        }
        if (fromDate != null) {
            filtersMap["from"] = fromDate.toString()
        }
        if (toDate != null) {
            filtersMap["to"] = toDate.toString()
        }
        if (language != null) {
            val langCode = when(language) {
                Language.RUSSIAN -> "ru"
                Language.ENGLISH -> "en"
                Language.GERMAN -> "de"
            }
            filtersMap["language"] = langCode
        }

        val response = searchArticlesApiService.filterArticles(filtersMap, page)
        val articles = mapToArticlesList(response.body()?.articles)
        return ArticlesListWrapper(response.isSuccessful, articles)
    }

    private fun mapToArticlesList(articlesDtoList: List<ArticleDto>?): List<ArticleItem> {
        return articlesDtoList?.map {
            var sourceImage: Int? = null
            if (it.source.id != null) {
                sourceImage = sourceImageRepository.getImageResourceIdBySourceId(it.source.id)
            }
            it.fromDto(ArticleSource(it.source.id, it.source.name, sourceImage))
        }
            ?: emptyList()
    }
}