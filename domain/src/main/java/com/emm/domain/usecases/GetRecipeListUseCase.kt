package com.emm.domain.usecases

import com.emm.domain.entitys.RecipeEntity
import com.emm.domain.repository.RecipeRepository
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

class GetRecipeListUseCase(
    private val recipeRepository: RecipeRepository
) {

    operator fun invoke(): Flow<ResultState<List<RecipeEntity>>> {
        return recipeRepository.getRecipeList()
    }

}