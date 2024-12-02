package com.example.aston_trainee_work.domain

import javax.inject.Inject

class GetSourcesListUseCase @Inject constructor(
    private val sourcesRepository: SourcesRepository) {

    suspend fun getSourcesList(): SourceListWrapper {
        return sourcesRepository.getSourcesList()
    }
}