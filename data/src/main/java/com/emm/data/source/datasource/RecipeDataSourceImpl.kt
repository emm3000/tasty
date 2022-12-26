package com.emm.data.source.datasource

import com.emm.data.source.api.RestClient
import com.emm.data.source.response.RecipeListResponse
import com.emm.data.utils.safeApiCall
import com.emm.domain.utils.ResultState

class RecipeDataSourceImpl(
    private val restApi: RestClient
) : RecipeDataSource {

    override suspend fun getRecipeList(take: Int, page: Int, filter: String): ResultState<RecipeListResponse> {
        return safeApiCall { restApi.recipeList(take, page, filter) }
    }

}