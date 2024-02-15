package com.example.aston_trainee_work.data.db

import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(
    private val sourceDao: SourceDao
): SourceRepository {
    override fun getImageSourceById(id: String): Int? {
        val imageSourceIds = sourceDao.getImageSourceId(id)
        return when {
            imageSourceIds.isEmpty() -> null
            else -> imageSourceIds[0]
        }
    }
}