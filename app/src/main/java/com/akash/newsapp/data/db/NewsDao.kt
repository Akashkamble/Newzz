package com.akash.newsapp.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.akash.newsapp.data.response.NewsArticle

@Dao
interface NewsDao{
    @Query("Select * from Articles")
    fun getHeadLines() : List<NewsArticle>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list : List<NewsArticle>)
}