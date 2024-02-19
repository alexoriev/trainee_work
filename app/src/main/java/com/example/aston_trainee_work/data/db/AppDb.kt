package com.example.aston_trainee_work.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SourceImageEntity::class], version = 1, exportSchema = false
)

abstract class AppDb : RoomDatabase() {
    abstract fun sourceDao(): SourceDao
}