package com.emm.domain.repository

import com.emm.domain.entitys.RecipeEntity
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipeList(): Flow<ResultState<List<RecipeEntity>>>

}