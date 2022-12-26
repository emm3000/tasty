package com.emm.data.source.response

import com.emm.domain.entitys.RecipeEntity
import com.emm.domain.entitys.RecipePaginationDataEntity
import kotlin.random.Random

data class RecipeListResponse(
    val data: RecipePaginationDataResponse,
    val statusMsg: String,
    val status: Int
)

data class RecipePaginationDataResponse(
    val data: List<RecipeResponse>,
    val total: Int,
    val page: Int,
    val limit: Int,
    val pages: Int,
    val hasNext: Boolean,
    val hasPrev: Boolean
)

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

fun RecipePaginationDataResponse.toDomainEntity(): RecipePaginationDataEntity {
    return RecipePaginationDataEntity(
        data = data.toDomainEntity(),
        total = total,
        page = page,
        limit = limit,
        pages = pages,
        hasNext = hasNext,
        hasPrev = hasPrev
    )
}

private fun List<RecipeResponse>.toDomainEntity(): List<RecipeEntity> {
    return map(RecipeResponse::toDomainEntity)
}

private fun RecipeResponse.toDomainEntity(): RecipeEntity {
    return RecipeEntity(
        id = id,
        ingredients = ingredients,
        latitude = latitude,
        longitude = longitude,
        portions = "${Random.nextInt(1, 6)} porciones",
        preparation = preparation,
        time = generateRandomTime(),
        title = title,
        urlImage = urlImage
    )
}

private fun generateRandomTime(): String {
    val randomHour = Random.nextInt(20, 60)
    val randomMinute = Random.nextInt(10, 20)
    return if (randomHour > 50) {
        "1h $randomMinute min"
    } else "$randomHour min"
}
