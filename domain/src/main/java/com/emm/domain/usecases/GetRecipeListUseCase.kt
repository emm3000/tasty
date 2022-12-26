package com.emm.domain.usecases

import com.emm.domain.entitys.RecipePaginationDataEntity
import com.emm.domain.repository.RecipeRepository
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

class GetRecipeListUseCase(private val recipeRepository: RecipeRepository) {

    data class InputParams(
        val take: Int = 10,
        val page: Int,
        val filter: String = "",
        val event: Event = Event.NONE
    )

    operator fun invoke(params: InputParams): Flow<ResultState<RecipePaginationDataEntity>> {
        return recipeRepository.getRecipeList(
            take = params.take,
            page = params.page,
            filter = params.filter
        )
    }

    enum class Event {
        ON_SCROLL,
        ON_TEXT_CHANGE,
        NONE
    }

}