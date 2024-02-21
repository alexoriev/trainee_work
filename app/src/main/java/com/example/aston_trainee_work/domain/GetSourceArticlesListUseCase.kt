package com.example.aston_trainee_work.domain

import javax.inject.Inject

class GetSourceArticlesListUseCase @Inject constructor(
    private val sourceArticlesRepository: SourceArticlesRepository
) {
    suspend fun getSourceArticlesList(sourceItem: SourceItem, page: Int): ArticlesListWrapper {
        return sourceArticlesRepository.getSourceArticlesList(sourceItem, page)
    }
}