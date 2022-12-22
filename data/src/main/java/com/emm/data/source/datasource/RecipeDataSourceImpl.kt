package com.emm.data.source.datasource

import com.emm.data.source.api.RestClient
import com.emm.data.source.response.RecipeResponse
import com.emm.data.utils.safeApiCall
import com.emm.domain.utils.ResultState

class RecipeDataSourceImpl(
    private val restApi: RestClient
) : RecipeDataSource {

    override suspend fun getRecipeList(): ResultState<List<RecipeResponse>> {
        return safeApiCall { restApi.recipeList() }
    }

}