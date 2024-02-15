package com.example.aston_trainee_work.utils

import com.example.aston_trainee_work.data.db.SourceRepository
import com.example.aston_trainee_work.data.dto.Source
import com.example.aston_trainee_work.domain.SourceWithImage
import javax.inject.Inject

class SourceConverter @Inject constructor(
    private val sourceRepository: SourceRepository
) {
    fun convert(source: Source): SourceWithImage {
        return if (source.id != null) {
            val imageSourceId = sourceRepository.getImageSourceById(source.id)
            SourceWithImage(source.id, source.name, imageSourceId)
        } else {
            SourceWithImage(source.id, source.name, null)
        }
    }
}