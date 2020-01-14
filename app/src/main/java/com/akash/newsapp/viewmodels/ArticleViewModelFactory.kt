package com.akash.newsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.newsapp.data.repositories.NewsRepository

/**
 * Created by Akash on 2020-01-14
 */
class ArticleViewModelFactory(
    private val newsRepo : NewsRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleViewModel(newsRepo) as T
    }
}
