package com.emm.data.source.api

import com.emm.data.source.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestClient {

    @GET("todos/1")
    suspend fun getUser(): Response<UserResponse>

}