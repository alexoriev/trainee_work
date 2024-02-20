package com.example.aston_trainee_work.data.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDateTime

@Database(
    entities = [SourceImageEntity::class,
        SavedArticleEntity::class], version = 1, exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDb : RoomDatabase() {
    abstract fun sourceImageDao(): SourceImageDao
    abstract fun savedArticleDao(): SavedArticleDao
}

object Converter {
    @TypeConverter
    @JvmStatic
    fun toString(dateTime: LocalDateTime): String {
        return dateTime.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    @JvmStatic
    fun toLocalDateTime(dateTime: String): LocalDateTime {
        return LocalDateTime.parse(dateTime)
    }
}