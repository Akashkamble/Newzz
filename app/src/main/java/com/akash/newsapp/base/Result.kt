package com.akash.newsapp.base

/**
 * Created by Akash on 2020-01-26
 */

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMessage: String, val showRetry: Boolean = true) : Result<Nothing>()
}