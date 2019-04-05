package com.akash.newsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akash.newsapp.data.response.NewsArticle

@Dao
interface NewsDao{
    @Query("Select * from Articles")
    fun getHeadLines() : List<NewsArticle>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list : List<NewsArticle>)
}