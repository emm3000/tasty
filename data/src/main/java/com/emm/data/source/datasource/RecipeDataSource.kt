package com.emm.data.source.datasource

import com.emm.data.source.response.RecipeListResponse
import com.emm.domain.utils.ResultState

interface RecipeDataSource {

    suspend fun getRecipeList(
        take: Int,
        page: Int,
        filter: String
    ): ResultState<RecipeListResponse>

}