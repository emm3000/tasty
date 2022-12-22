package com.emm.data.source.api

import com.emm.data.source.response.RecipeResponse
import com.emm.data.source.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestClient {

    @GET("todos/1")
    suspend fun getUser(): Response<UserResponse>

    @GET("https://run.mocky.io/v3/4babaea2-de0c-4daa-8beb-9201ec2e1db2")
    suspend fun recipeList(): Response<List<RecipeResponse>>

}