package com.example.aston_trainee_work.data.db

interface SourceImageRepository {
    fun getImageSourceById(id: String): Int?
}