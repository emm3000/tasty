package com.emm.data.source.api

import com.emm.data.constants.DataConstants.API_PLACE_BASE_URL
import com.emm.data.source.response.RecipeListResponse
import com.emm.data.source.response.PlaceByRecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RestClient {

    @GET("recipes")
    suspend fun recipeList(
        @Query("take") take: Int,
        @Query("page") page: Int,
        @Query("filter") filter: String
    ): Response<RecipeListResponse>

    @GET(API_PLACE_BASE_URL)
    suspend fun getMarkersByRecipe(
        @QueryMap(encoded = true) query: Map<String, String>
    ): Response<PlaceByRecipeResponse>

}