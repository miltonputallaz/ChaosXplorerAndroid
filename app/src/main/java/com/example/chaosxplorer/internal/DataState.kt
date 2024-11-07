package com.example.chaosxplorer.internal

sealed interface DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>
    data class Error(val exception: Throwable) : DataState<Nothing>
    data object Loading : DataState<Nothing>
}