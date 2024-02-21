package com.example.aston_trainee_work.domain

import javax.inject.Inject

class SearchArticlesUseCase @Inject constructor(
    private val searchArticlesRepository: SearchArticlesRepository) {

    suspend fun searchArticles(query: String, page: Int): ArticlesListWrapper {
        return searchArticlesRepository.searchArticles(query, page)
    }
}