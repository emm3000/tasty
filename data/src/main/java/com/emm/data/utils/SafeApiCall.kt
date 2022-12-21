package com.emm.data.utils

import com.emm.domain.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

suspend inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> Response<T>,
): ResultState<T> {
    return withContext(Dispatchers.IO) {
        try {
            val result = apiCall()
            if (result.isSuccessful && result.body() != null) {
                ResultState.Success(data = result.body()!!)
            } else {
                ResultState.Error(HttpException(result))
            }
        } catch (e: Exception) {
            ResultState.Error(e, e.localizedMessage)
        }
    }
}