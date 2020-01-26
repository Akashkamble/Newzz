package com.akash.newsapp.viewmodels

/**
 * Created by Akash on 2019-10-02
 */
data class ErrorState(
    val listener: ErrorStateRetryListener,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val showRetry: Boolean = false,
    val errorMessage: String? = ""
) {
    fun onRetry() {
        listener.onRetry()
    }

    interface ErrorStateRetryListener {
        fun onRetry()
    }
}