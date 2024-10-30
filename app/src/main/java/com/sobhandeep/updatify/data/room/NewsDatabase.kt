package com.sobhandeep.updatify.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sobhandeep.updatify.domain.model.Article

@Database(entities = [Article::class], version = 2, exportSchema = false)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao
}