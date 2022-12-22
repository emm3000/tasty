package com.emm.data.source.datasource

import com.emm.data.source.response.RecipeResponse
import com.emm.domain.utils.ResultState

interface RecipeDataSource {

    suspend fun getRecipeList(): ResultState<List<RecipeResponse>>

}