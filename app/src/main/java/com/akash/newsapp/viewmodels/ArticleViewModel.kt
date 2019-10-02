package com.akash.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.akash.newsapp.adapters.ArticleRowViewModel
import com.akash.newsapp.base.BaseRowModel
import com.akash.newsapp.base.Event
import com.akash.newsapp.data.repositories.NewsRepository
import kotlinx.coroutines.*

class ArticleViewModel constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val TAG = ArticleViewModel::class.java.simpleName
    private val job = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main) + job
    private val _event = MutableLiveData<Event<ViewEvent>>()
    val event = _event

    private val _articleList = MutableLiveData<MutableList<BaseRowModel>>().apply {
        value = mutableListOf()
    }
    val articleList: LiveData<MutableList<BaseRowModel>> = _articleList

    val gotList = Transformations.map(articleList) {
        it.size > 0
    }

    fun getArticlesByCategory(category: String, page: Int = 1, isFromSwipeRefresh: Boolean = false) {
        val tempList = mutableListOf<BaseRowModel>()
        viewModelScope.launch {
            try {
                val response = newsRepository.getArticlesByCategoryAsync(category).await()
                withContext(Dispatchers.Main) {
                    response.articles.toMutableList().forEach { article ->
                        article.urlToImage?.let {
                            tempList.add(ArticleRowViewModel(article, this@ArticleViewModel))
                        }
                    }
                    _articleList.value = tempList
                    if (isFromSwipeRefresh) {
                        _event.value = Event(ViewEvent.FinishRefresh)
                    }
                }

            } catch (e: Exception) {
                Log.e(TAG, "exception : ${e.localizedMessage}")
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    _event.value = Event(ViewEvent.ShowToast(e.localizedMessage))
                }
            }
        }
    }

    fun openArticleInBrowser(articleRowViewModel: ArticleRowViewModel) {
        _event.value = Event(ViewEvent.NavigateToBrowser(articleRowViewModel.url))
    }

    sealed class ViewEvent {
        data class NavigateToBrowser(val url: String) : ViewEvent()
        data class ShowToast(val toastMessage: String) : ViewEvent()
        object FinishRefresh : ViewEvent()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}