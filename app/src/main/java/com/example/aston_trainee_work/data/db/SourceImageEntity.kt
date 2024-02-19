package com.example.aston_trainee_work.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SourceImageEntity(
    @PrimaryKey
    val id: String,
    val imageResourceId: Int
)