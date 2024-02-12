package com.example.aston_trainee_work.data

import com.example.aston_trainee_work.data.api.ArticlesApiServiceKOKLIN
import com.example.aston_trainee_work.domain.ArticleItem
import com.example.aston_trainee_work.domain.ArticlesRepositoryKOKLIN
import com.example.aston_trainee_work.domain.Category
import javax.inject.Inject

class ArticlesRepositoryImplKOKLIN @Inject constructor(
    private var apiService: ArticlesApiServiceKOKLIN
) : ArticlesRepositoryKOKLIN {
    override suspend fun getHeadlinesNewsList(category: Category): List<ArticleItem> {
        return apiService.getTopHeadlinesNews(category, 1).body()?.articles?.map { it.fromDto() }
            ?: emptyList()
    }
}