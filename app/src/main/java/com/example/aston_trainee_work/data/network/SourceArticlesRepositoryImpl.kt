package com.example.aston_trainee_work.data.network

import com.example.aston_trainee_work.data.api.SourceArticlesApiService
import com.example.aston_trainee_work.domain.ArticlesListWrapper
import com.example.aston_trainee_work.domain.ArticleSource
import com.example.aston_trainee_work.domain.SourceArticlesRepository
import com.example.aston_trainee_work.domain.SourceItem
import javax.inject.Inject

class SourceArticlesRepositoryImpl @Inject constructor(
    private var apiService: SourceArticlesApiService,
) : SourceArticlesRepository {

    override suspend fun getSourceArticlesList(
        source: SourceItem,
        page: Int
    ): ArticlesListWrapper {
        val response = apiService.getSourceArticles(source.id, page)
        val articles =
            response.body()?.articles
                ?.map { it.fromDto(ArticleSource(source.id, source.name, source.imageResourceId)) }
                ?: emptyList()
        return ArticlesListWrapper(response.isSuccessful, articles)
    }
}