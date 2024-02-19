package com.example.aston_trainee_work.domain

interface SourcesRepository {

    suspend fun getSourcesList(): List<SourceItem>
}