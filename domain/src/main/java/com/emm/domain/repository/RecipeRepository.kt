package com.emm.domain.repository

import com.emm.domain.entitys.RecipePaginationDataEntity
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipeList(
        take: Int,
        page: Int,
        filter: String
    ): Flow<ResultState<RecipePaginationDataEntity>>

}