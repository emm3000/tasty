package com.emm.tasty.models

import com.emm.domain.entitys.RecipeEntity

data class RecipeModel(
    val id: Int,
    val ingredients: List<String>,
    val latitude: String,
    val longitude: String,
    val portions: String,
    val preparation: List<String>,
    val time: String,
    val title: String,
    val urlImage: String
)

fun List<RecipeEntity>.toUIModel(): List<RecipeModel> {
    return map(RecipeEntity::toUIModel)
}

private fun RecipeEntity.toUIModel(): RecipeModel {
    return RecipeModel(
        id = id,
        ingredients = ingredients.shuffled(),
        latitude = latitude,
        longitude = longitude,
        portions = portions,
        preparation = preparation.shuffled(),
        time = time,
        title = title,
        urlImage = urlImage
    )
}