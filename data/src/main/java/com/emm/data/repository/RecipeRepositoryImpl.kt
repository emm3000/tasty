package com.emm.data.repository

import com.emm.data.source.datasource.RecipeDataSource
import com.emm.data.source.response.toDomainEntity
import com.emm.domain.entitys.RecipePaginationDataEntity
import com.emm.domain.repository.RecipeRepository
import com.emm.domain.utils.ResultState
import com.emm.domain.utils.buildFlowWithResultState
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val recipeDataSource: RecipeDataSource
) : RecipeRepository {

    override fun getRecipeList(take: Int, page: Int, filter: String): Flow<ResultState<RecipePaginationDataEntity>> {
        return buildFlowWithResultState(
            fetchData = { recipeDataSource.getRecipeList(take, page, filter) },
            mapToDomainModel = { it.data.toDomainEntity() }
        )
    }

}