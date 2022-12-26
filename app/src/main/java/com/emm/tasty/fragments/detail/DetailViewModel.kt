package com.emm.tasty.fragments.detail

import androidx.lifecycle.ViewModel
import com.emm.tasty.models.RecipeModel
import kotlinx.coroutines.flow.MutableStateFlow

class DetailViewModel(
    recipeModel: RecipeModel
) : ViewModel() {

    val detailState: MutableStateFlow<RecipeModel> = MutableStateFlow(recipeModel)

}