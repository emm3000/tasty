package com.emm.tasty.fragments.detail

import androidx.lifecycle.ViewModel
import com.emm.tasty.models.RecipeModel
import kotlinx.coroutines.flow.MutableStateFlow

class DetailViewModel(
    private val recipeModel: RecipeModel
) : ViewModel() {

    val detailState: MutableStateFlow<RecipeModel> = MutableStateFlow(recipeModel.copy(
        preparation = recipeModel.preparation.plus(recipeModel.preparation).plus(recipeModel.preparation),
        ingredients = recipeModel.ingredients.plus(recipeModel.ingredients).plus(recipeModel.ingredients)
    ))

}