package com.example.aston_trainee_work.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SourceEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val imageSourceId: Int
)