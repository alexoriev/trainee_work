package com.example.aston_trainee_work.data.network

import com.example.aston_trainee_work.data.api.SourcesApiService
import com.example.aston_trainee_work.domain.SourceListWrapper
import com.example.aston_trainee_work.domain.SourcesRepository
import com.example.aston_trainee_work.utils.SourceConverter
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private var apiService: SourcesApiService,
    private val converter: SourceConverter
) : SourcesRepository {

    override suspend fun getSourcesList(): SourceListWrapper {
        val response = apiService.getSources()
        val sources = response.body()?.sources
            ?.map { converter.convertSource(it) }
            ?: emptyList()
        return SourceListWrapper(response.isSuccessful, sources)
    }
}