package com.emm.domain.fakerepositories

import com.emm.domain.entitys.RecipePaginationDataEntity
import com.emm.domain.repository.RecipeRepository
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecipeRepository: RecipeRepository {

    override fun getRecipeList(take: Int, page: Int, filter: String): Flow<ResultState<RecipePaginationDataEntity>> {
        return flow {
            emit(
                ResultState.Success(
                    RecipePaginationDataEntity(
                        data = emptyList(),
                        total = 1,
                        page = page,
                        limit = 1,
                        pages = 1,
                        hasNext = false,
                        hasPrev = false
                    )
                )
            )
        }
    }
}