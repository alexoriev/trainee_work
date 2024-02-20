package com.example.aston_trainee_work.domain

interface SourceImageRepository {
    fun getImageResourceIdBySourceId(id: String): Int?
}