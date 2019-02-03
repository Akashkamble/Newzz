package com.akash.newsapp.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.akash.newsapp.data.repositories.NewsRepository
import com.akash.newsapp.data.response.NewsArticle
import kotlinx.coroutines.*
import java.lang.Exception

class GeneralViewModel constructor(val newsRepository: NewsRepository) : ViewModel() {
    private val TAG = GeneralViewModel::class.java.simpleName
    private val generalArticleList = MutableLiveData<List<NewsArticle>>().apply {
        value = emptyList()
    }
    private val message = MutableLiveData<String>().apply {
        value = ""
    }
    val viewModelMessage: LiveData<String> = message
    val articleList: LiveData<List<NewsArticle>> = generalArticleList
    fun getArticles(page : Int = 1) {
        Log.e(TAG, "getArticles invoked ")
        GlobalScope.launch {
            try {
                val response = newsRepository.getArticlesByCategoryAsync("general").await()
                withContext(Dispatchers.Main){
                    generalArticleList.value = response.articles
                }

            } catch (e: Exception) {
                Log.e(TAG,"exception : ${e.localizedMessage}")
                withContext(Dispatchers.Main){
                    message.value = e.localizedMessage
                }
            }
        }
    }

}