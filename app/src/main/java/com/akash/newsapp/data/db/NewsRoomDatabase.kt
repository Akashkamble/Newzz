package com.akash.newsapp.data.db

import android.arch.persistence.room.*
import com.akash.newsapp.data.converters.DateConverters
import com.akash.newsapp.data.response.NewsArticle

@Database(entities = arrayOf(NewsArticle::class), version = 1)
@TypeConverters(DateConverters::class)
abstract class NewsRoomDataBase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao

    /*companion object {
        @Volatile
        private var INSTANCE : NewsRoomDataBase? = null
        fun getDatabase(context: Context): NewsRoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsRoomDataBase::class.java,
                    "News_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }*/
}