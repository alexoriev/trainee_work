package com.example.aston_trainee_work.data.db

import com.example.aston_trainee_work.domain.SourceImageRepository
import javax.inject.Inject

class SourceImageRepositoryImpl @Inject constructor(
    private val sourceImageDao: SourceImageDao
): SourceImageRepository {
    override fun getImageResourceIdBySourceId(id: String): Int? {
        val imageSourceIds = sourceImageDao.getImageSourceId(id)
        return when {
            imageSourceIds.isEmpty() -> null
            else -> imageSourceIds[0]
        }
    }
}