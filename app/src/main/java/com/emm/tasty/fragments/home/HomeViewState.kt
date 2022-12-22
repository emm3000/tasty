package com.emm.tasty.fragments.home

import com.emm.tasty.models.RecipeModel

data class HomeViewState(
    val recipeListFilter: List<RecipeModel> = emptyList(),
    val originalRecipeList: List<RecipeModel> = emptyList(),
    val showLoading: Boolean = false,
    val errorMessage: String? = null
)