package com.example.aston_trainee_work.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SourceDao {

    @Query("SELECT imageSourceId FROM SourceEntity WHERE id = :id")
    fun getImageSourceId(id: String): List<Int>

    @Insert
    fun insert(source: SourceEntity)

    @Insert
    fun insert(sourceList: List<SourceEntity>)
}