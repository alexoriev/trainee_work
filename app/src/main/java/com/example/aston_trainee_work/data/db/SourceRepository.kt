package com.example.aston_trainee_work.data.db

interface SourceRepository {
    fun getImageSourceById(id: String): Int?
}