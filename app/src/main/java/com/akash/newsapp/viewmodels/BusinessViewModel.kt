package com.akash.newsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.akash.newsapp.data.repositories.NewsRepository
import com.akash.newsapp.data.response.NewsArticle
import kotlinx.coroutines.*
import java.lang.Exception

class BusinessViewModel constructor(val newsRepository: NewsRepository) : ViewModel() {
    private val TAG = GeneralViewModel::class.java.simpleName
    private val generalArticleList = MutableLiveData<List<NewsArticle>>().apply {
        value = emptyList()
    }
    private val message = MutableLiveData<String>().apply {
        value = ""
    }
    val viewModelMessage: LiveData<String> = message
    val articleList: LiveData<List<NewsArticle>> = generalArticleList
    fun getArticles() {
        GlobalScope.launch {
            try {
                val response = newsRepository.getArticlesByCategoryAsync("business").await()
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