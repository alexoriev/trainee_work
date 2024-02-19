package com.example.aston_trainee_work.data.network

import com.example.aston_trainee_work.data.api.SourcesApiService
import com.example.aston_trainee_work.domain.SourceItem
import com.example.aston_trainee_work.domain.SourcesRepository
import com.example.aston_trainee_work.utils.SourceConverter
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private var apiService: SourcesApiService,
    private val converter: SourceConverter
) : SourcesRepository {
    override suspend fun getSourcesList(): List<SourceItem> {
        return apiService.getSources().body()?.sources?.map { converter.convertSource(it) }
            ?: emptyList()
    }
}