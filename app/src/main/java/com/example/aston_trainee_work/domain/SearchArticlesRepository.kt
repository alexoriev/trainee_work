package com.example.aston_trainee_work.domain

import java.time.LocalDate

interface SearchArticlesRepository {
    suspend fun searchArticles(query: String, page: Int): ArticlesListWrapper
    suspend fun filterArticles(sortingType: SortingType?,
                               fromDate: LocalDate?,
                               toDate: LocalDate?,
                               language: Language?,
                               page: Int): ArticlesListWrapper
}