package com.akash.newsapp.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.akash.newsapp.data.repositories.NewsRepository
import com.akash.newsapp.data.response.NewsArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class NewsViewModel constructor(val newsRepository: NewsRepository) : ViewModel() {
    val TAG = NewsViewModel::class.java.simpleName
    private val mutableHeadLineList = MutableLiveData<List<NewsArticle>>()
        .apply { value = emptyList<NewsArticle>() }
    private val mutableErrorMessage = MutableLiveData<String>()
        .apply { value = "" }
    val errorMessage: LiveData<String> = mutableErrorMessage
    val headLineList: LiveData<List<NewsArticle>> = mutableHeadLineList
    fun getHeadLines() {
        runBlocking(Dispatchers.IO) {
            try {
                val response = getArticlesByCategory().await()
                mutableHeadLineList.postValue(response.articles)

            } catch (e: Exception) {
                Log.e(TAG,"exception : ${e.localizedMessage}")
                mutableErrorMessage.postValue(e.localizedMessage)
            }
        }
    }

    private suspend fun getArticlesByCategory() = newsRepository.getArticlesByCategoryAsync("science")
    private suspend fun getScienceArticlesAsync() = newsRepository.getArticlesByCategoryAsync("science")
}