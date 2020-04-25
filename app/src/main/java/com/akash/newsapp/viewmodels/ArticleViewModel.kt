package com.akash.newsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.akash.newsapp.base.BaseRowModel
import com.akash.newsapp.base.Event
import com.akash.newsapp.base.Result
import com.akash.newsapp.base.constants.Category
import com.akash.newsapp.data.repositories.NewsRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel(), ErrorState.ErrorStateRetryListener {

    private val job = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main) + job
    private val _event = MutableLiveData<Event<ViewEvent>>()
    val event = _event
    var category: String = Category.GENERAL

    private val _errorState = ErrorState(this as ErrorState.ErrorStateRetryListener)
    val errorState = MutableLiveData<ErrorState>().apply { value = _errorState }

    private val _isLoading = MutableLiveData<Boolean>().apply { value = true }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _articleList = MutableLiveData<MutableList<BaseRowModel>>().apply {
        value = mutableListOf()
    }

    val articleList: LiveData<MutableList<BaseRowModel>> = _articleList
    val gotList = Transformations.map(articleList) {
        it.size > 0
    }

    override fun onRetry() {
        getArticlesByCategory(category)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private suspend fun getArticleListOrErrorMessage(
        category: String,
        page: Int,
        tempList: MutableList<BaseRowModel>,
        isFromSwipeRefresh: Boolean
    ) {
        when (val result = newsRepository.getArticlesByCategoryAsync(category, page)) {
            is Result.Success -> {
                withContext(Dispatchers.Main) {
                    result.data.articles.toMutableList().forEach { article ->
                        tempList.add(
                            ArticleRowViewModel(
                                article,
                                this@ArticleViewModel
                            )
                        )
                    }
                    _articleList.value = tempList
                    if (isFromSwipeRefresh) {
                        _event.value = Event(ViewEvent.FinishRefresh)
                    }
                    errorState.value = _errorState.copy(isLoading = false)
                    _isLoading.value = false
                }
            }
            is Result.Error -> {
                withContext(Dispatchers.Main) {
                    errorState.value = _errorState.copy(
                        isError = true,
                        errorMessage = result.errorMessage,
                        showRetry = result.showRetry
                    )
                }
            }
        }
    }

    fun getArticlesByCategory(
        category: String,
        page: Int = 1,
        isFromSwipeRefresh: Boolean = false
    ) {
        val tempList = mutableListOf<BaseRowModel>()
        errorState.value = _errorState.copy(isLoading = true)
        viewModelScope.launch {
            getArticleListOrErrorMessage(category, page, tempList, isFromSwipeRefresh)
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

}
