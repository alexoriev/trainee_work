package com.example.aston_trainee_work.domain

import java.io.Serializable

data class SourceItem(
    val id: String,
    val name: String,
    val country: String,
    val category: String,
    val imageResourceId: Int?
): Serializable