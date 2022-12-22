package com.emm.tasty.fragments.home

import com.emm.tasty.models.RecipeModel

data class HomeViewState(
    val recipeListFilter: List<RecipeModel>? = null,
    val originalRecipeList: List<RecipeModel>? = null,
    val showLoading: Boolean = false,
    val errorMessage: String? = null
)