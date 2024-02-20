package com.example.aston_trainee_work.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SourceImageDao {

    @Query("SELECT imageResourceId FROM SourceImageEntity WHERE id = :id")
    fun getImageSourceId(id: String): List<Int>

    @Insert
    fun insert(source: SourceImageEntity)

    @Insert
    fun insert(sourceList: List<SourceImageEntity>)
}