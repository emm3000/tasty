package com.emm.data.source.response

import com.emm.domain.entitys.RecipeEntity

data class RecipeResponse(
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

fun List<RecipeResponse>.toDomainEntity(): List<RecipeEntity> {
    return map(RecipeResponse::toDomainEntity)
}

private fun RecipeResponse.toDomainEntity(): RecipeEntity {
    return RecipeEntity(
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
