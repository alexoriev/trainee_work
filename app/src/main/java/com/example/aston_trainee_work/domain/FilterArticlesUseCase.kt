package com.example.aston_trainee_work.domain

import java.time.LocalDate
import javax.inject.Inject

class FilterArticlesUseCase @Inject constructor(
    private val searchArticlesRepository: SearchArticlesRepository) {

    suspend fun filterArticles(sortingType: SortingType?,
                               fromDate: LocalDate?,
                               toDate: LocalDate?,
                               language: Language?,
                               page: Int): ArticlesListWrapper {
        return searchArticlesRepository.filterArticles(sortingType,
            fromDate,
            toDate,
            language,
            page)
    }
}