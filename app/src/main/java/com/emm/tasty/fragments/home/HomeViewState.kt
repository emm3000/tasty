package com.emm.tasty.fragments.home

import com.emm.domain.entitys.RecipePaginationDataEntity
import com.emm.tasty.models.RecipeModel

data class HomeViewState(
    val recipeList: List<RecipeModel>? = null,
    val paginationInfo: PaginationInfo? = null,
    val showLoading: Boolean = false,
    val errorMessage: String? = null
)

data class PaginationInfo(
    val total: Int,
    val page: Int,
    val limit: Int,
    val pages: Int,
    val hasNext: Boolean,
    val hasPrev: Boolean
)

fun RecipePaginationDataEntity.toUIModel(): PaginationInfo {
    return PaginationInfo(
        total = total,
        page = page,
        limit = limit,
        pages = pages,
        hasNext = hasNext,
        hasPrev = hasPrev
    )
}