package com.emm.domain.utils

sealed class ResultState<out T> {
    class Success<out T>(val data: T) : ResultState<T>()
    class Error(val error: Throwable?, val message: String? = null) : ResultState<Nothing>()
}

fun <T, X> ResultState<T>.mapper(mapper: (T) -> X): ResultState<X> {
    return when (this) {
        is ResultState.Error -> ResultState.Error(this.error)
        is ResultState.Success -> ResultState.Success(mapper(this.data))
    }
}