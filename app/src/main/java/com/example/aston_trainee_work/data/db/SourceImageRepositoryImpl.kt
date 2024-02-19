package com.example.aston_trainee_work.data.db

import javax.inject.Inject

class SourceImageRepositoryImpl @Inject constructor(
    private val sourceDao: SourceDao
): SourceImageRepository {
    override fun getImageSourceById(id: String): Int? {
        val imageSourceIds = sourceDao.getImageSourceId(id)
        return when {
            imageSourceIds.isEmpty() -> null
            else -> imageSourceIds[0]
        }
    }
}